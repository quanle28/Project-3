package com.javaweb.repository.custom.impl;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BuildingRepositoryCustomImpl implements BuildingRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    public static void joinTable (BuildingDTO buildingDTO, StringBuilder sql){
        Long staffId = buildingDTO.getStaffId();
        if (staffId != null && !staffId.equals("")) {
            sql.append(" INNER JOIN assignmentbuilding ON assignmentbuilding.buildingid = b.id ");
        }
        Long rentAreaTo = buildingDTO.getAreaTo();
        Long rentAreaFrom = buildingDTO.getAreaFrom();
        if (rentAreaTo != null && !rentAreaTo.equals("") || rentAreaFrom != null && !rentAreaFrom.equals("")) {
            sql.append(" INNER JOIN rentarea ON b.id = rentarea.buildingid ");
        }
    }

    public static void queryNomal(BuildingDTO buildingDTO, StringBuilder where) {
        try {
            Field[] fields = BuildingDTO.class.getDeclaredFields();
            for (Field item : fields) {
                item.setAccessible(true);
                String fieldName = item.getName();
                if (!fieldName.equals("staffId") && !fieldName.equals("typeCode")
                        && !fieldName.startsWith("area") && !fieldName.startsWith("rentPrice")) {
                    Object value = item.get(buildingDTO);
                    if (value != null && !value.equals("")) {
                        if (item.getType().getName().equals("java.lang.Long") || item.getType().getName().equals("java.lang.Integer")) {
                            where.append(" AND b."+fieldName+" = "+value+" ");
                        }else if(item.getType().getName().equals("java.lang.String")){
                            where.append(" AND b."+fieldName+" LIKE '%"+value+"%' ");
                        }
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void querySpecial(BuildingDTO buildingDTO, StringBuilder where) {
        Long staffId = buildingDTO.getStaffId();
        if (staffId != null && !staffId.equals("")) {
            where.append(" AND assignmentbuilding.staffid = "+staffId+" ");
        }
        Long rentAreaTo = buildingDTO.getAreaTo();
        Long rentAreaFrom = buildingDTO.getAreaFrom();
        if (rentAreaTo != null && !rentAreaTo.equals("") || rentAreaFrom != null && !rentAreaFrom.equals("")) {
            // Dùng EXISTS không cần phải dùng hàm INNER JOIN
            where.append(" AND EXISTS (SELECT * FROM rentarea r WHERE r.buildingid = b.id ");
            if (rentAreaFrom != null) {
                where.append(" AND r.value >= "+rentAreaFrom);
            }
            if (rentAreaTo != null) {
                where.append(" AND r.value <= "+rentAreaTo);
            }
            where.append(" ) ");
        }
        Long rentPriceTo = buildingDTO.getRentPriceTo();
        Long rentPriceFrom = buildingDTO.getRentPriceFrom();
        if (rentPriceTo != null && !rentPriceTo.equals("") || rentPriceFrom != null && !rentPriceFrom.equals("")) {
            if (rentPriceFrom != null) {
                where.append(" AND b.rentprice >= "+rentPriceFrom);
            }
            if (rentPriceTo != null) {
                where.append(" AND b.rentprice <= "+ rentPriceTo);
            }
        }
        // java 7
//		if (typeCode !=null && typeCode.size() !=0) {
//			List<String> code = new ArrayList<String>();
//			for (String it : typeCode) {
//				code.add("'"+it+"'");
//			}
//			where.append(" AND renttype.code IN("+String.join(",", code)+") ");
//		}

        // java 8
        List<String> typeCode = buildingDTO.getTypeCode();
        if (typeCode !=null && typeCode.size() !=0) {
            where.append(" AND ( ");
            String sql = typeCode.stream().map(it -> "b.type LIKE '%"+it+"%' ").collect(Collectors.joining(" OR "));
            where.append(sql);
            where.append(" ) ");
        }
    }

    @Override
    public List<BuildingEntity> findBuildingByRequest(BuildingDTO buildingDTO) {
        StringBuilder sql = new StringBuilder("SELECT b.* FROM building b ");
        joinTable(buildingDTO, sql);
        StringBuilder where = new StringBuilder(" WHERE 1 = 1 ");
        queryNomal(buildingDTO, where);
        querySpecial(buildingDTO, where);
        where.append(" GROUP BY b.id ");
        sql.append(where);
        Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
        return query.getResultList();
    }
}
