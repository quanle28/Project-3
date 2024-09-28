<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="buildingAPI" value="/api/building"/>
<html>
<head>
    <title>Thêm tòa nhà</title>
</head>
<body>
<div class="main-content">
    <div class="main-content">
        <div class="main-content-inner">
            <div class="breadcrumbs" id="breadcrumbs">
                <script type="text/javascript">
                    try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
                </script>

                <ul class="breadcrumb">
                    <li>
                        <i class="ace-icon fa fa-home home-icon"></i>
                        <a href="#">Home</a>
                    </li>
                    <li class="active">Dashboard</li>
                </ul><!-- /.breadcrumb -->
            </div>

            <div class="page-content">
                <div class="page-header">
                    <h1 style="font-family: 'Times New Roman', Times, serif;">
                        Sửa hoặc thêm tòa nhà
                    </h1>
                </div><!-- /.page-header -->

                <div class="row" style="font-family: 'Times New Roman', Times, serif;">
                    <form:form modelAttribute="editBuilding" id="listForm" method="get">
                        <div class="col-xs-12 widget-container-col ui-sortable">
                            <form class="form-horizontal" role="form" id="form-edit">
                                <div class="form-group">
                                    <label class="col-xs-3">Tên tòa nhà</label>
                                    <div class="col-xs-9">
<%--                                        <input type="text" name="name" id="name" class="form-control" value="${editBuilding.name}">--%>
                                        <form:input class="form-control" path="name"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Quận</label>
                                    <div class="col-xs-2">
                                        <form:select class="form-control" path="district">
                                            <form:option value="">---Chọn Quận---</form:option>
                                            <form:options items="${listDistricts}"/>
                                        </form:select>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Phường</label>
                                    <div class="col-xs-9">
<%--                                        <input type="text" name="ward" id="ward" class="form-control">--%>
                                        <form:input class="form-control" path="ward"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Đường</label>
                                    <div class="col-xs-9">
<%--                                        <input type="text" name="street" id="street" class="form-control">--%>
                                        <form:input class="form-control" path="street"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Kết cấu</label>
                                    <div class="col-xs-9">
                                        <form:input class="form-control" path="structure"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Số tầng hầm</label>
                                    <div class="col-xs-9">
<%--                                        <input type="number" name="numberofbasement" id="numberofbasement" class="form-control">--%>
                                        <form:input class="form-control" path="numberOfBasement"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Diện tích sàn</label>
                                    <div class="col-xs-9">
<%--                                        <input type="number" name="floorarea" id="floorarea" class="form-control">--%>
                                        <form:input class="form-control" path="floorArea"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Hướng</label>
                                    <div class="col-xs-9">
<%--                                        <input type="text" name="direction" id="direction" class="form-control">--%>
                                        <form:input class="form-control" path="direction"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Hạng</label>
                                    <div class="col-xs-9">
<%--                                        <input type="number" name="level" id="level" class="form-control">--%>
                                        <form:input class="form-control" path="level"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Diện tích thuê</label>
                                    <div class="col-xs-9">
<%--                                        <input type="text" name="rentarea" id="rentarea" class="form-control">--%>
                                        <form:input class="form-control" path="rentArea"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Giá thuê</label>
                                    <div class="col-xs-9">
<%--                                        <input type="number" name="rentprice" id="rentprice" class="form-control">--%>
                                        <form:input class="form-control" path="rentPrice"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Mô tả giá</label>
                                    <div class="col-xs-9">
<%--                                        <input type="text" name="rentpricedescription" id="rentpricedescription" class="form-control">--%>
                                        <form:input class="form-control" path="rentPriceDescription"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Phí dịch vụ</label>
                                    <div class="col-xs-9">
<%--                                        <input type="number" name="servicefee" id="servicefee" class="form-control">--%>
                                        <form:input class="form-control" path="serviceFee"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Phí ô tô</label>
                                    <div class="col-xs-9">
<%--                                        <input type="number" name="carfee" id="carfee" class="form-control">--%>
                                        <form:input class="form-control" path="carFee"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Phí mô tô</label>
                                    <div class="col-xs-9">
<%--                                        <input type="number" name="motorbikefee" id="motorbikefee" class="form-control">--%>
                                        <form:input class="form-control" path="motorbikeFee"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Phí ngoài giờ</label>
                                    <div class="col-xs-9">
<%--                                        <input type="number" name="overtimefee" id="overtimefee" class="form-control">--%>
                                        <form:input class="form-control" path="overtimeFee"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Tiền điện</label>
                                    <div class="col-xs-9">
<%--                                        <input type="number" name="electricityfee" id="electricityfee" class="form-control">--%>
                                        <form:input class="form-control" path="electricityFee"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Đặt cọc</label>
                                    <div class="col-xs-9">
<%--                                        <input type="number" name="deposit" id="deposit" class="form-control">--%>
                                        <form:input class="form-control" path="deposit"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Thanh toán</label>
                                    <div class="col-xs-9">
<%--                                        <input type="number" name="payment" id="payment" class="form-control">--%>
                                        <form:input class="form-control" path="payment"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Thời hạn thuê</label>
                                    <div class="col-xs-9">
<%--                                        <input type="text" name="renttime" id="renttime" class="form-control">--%>
                                        <form:input class="form-control" path="rentTime"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Thời gian trang trí</label>
                                    <div class="col-xs-9">
<%--                                        <input type="text" name="decorationtime" id="decorationtime" class="form-control">--%>
                                        <form:input class="form-control" path="decorationTime"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Tên quản lý</label>
                                    <div class="col-xs-9">
<%--                                        <input type="text" name="managername" id="managername" class="form-control">--%>
                                        <form:input class="form-control" path="managerName"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">SĐT quản lý</label>
                                    <div class="col-xs-9">
<%--                                        <input type="number" name="managerphonenumber" id="managerphonenumber" class="form-control">--%>
                                        <form:input class="form-control" path="managerPhone"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Phí môi giới</label>
                                    <div class="col-xs-9">
<%--                                        <input type="number" name="brokeragefee" id="brokeragefee" class="form-control">--%>
                                        <form:input class="form-control" path="brokerageFee"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Loại tòa nhà</label>
                                    <div class="col-xs-9">
                                        <form:checkboxes items="${listTypeCodes}" path="typeCode"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Ghi chú</label>
                                    <div class="col-xs-9">
<%--                                        <input type="text" name="note" id="note" class="form-control">--%>
                                        <form:input class="form-control" path="note"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-3 no-padding-right">Hình đại diện</label>
                                    <input class="col-sm-3 no-padding-right"  type="file" id="uploadImage" />
                                    <div class="col-sm-9">
                                        <c:if test="${not empty editBuilding.image}">
                                            <c:set var="imagePath" value="/repository${editBuilding.image}"/>
                                            <img src="${imagePath}" id="viewImage" width="300px" height="300px" style="margin-top: 50px">
                                        </c:if>
                                        <c:if test="${empty editBuilding.image}">
                                            <img src="/admin/image/default.png" id="viewImage" width="300px" height="300px">
                                        </c:if>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3"></label>
                                    <div class="col-xs-9">
                                        <c:if test="${not empty editBuilding.id}">
                                            <button type="button" class="btn btn-primary" title="Sửa tòa nhà" id="btnAddOrUpdateBuilding">Sửa tòa nhà</button>
                                            <button type="button" class="btn btn-primary" title="Hủy thao tác" id="btnCancel">Hủy thao tác</button>
                                            <img src="/img/loading.gif" style="display: none; height: 100px" id="loading_image">
                                        </c:if>
                                        <c:if test="${empty editBuilding.id}">
                                            <button type="button" class="btn btn-primary" title="Thêm tòa nhà" id="btnAddOrUpdateBuilding">Thêm tòa nhà</button>
                                            <button type="button" class="btn btn-primary" title="Hủy thao tác" id="btnCancel">Hủy thao tác</button>
                                            <img src="/img/loading.gif" style="display: none; height: 100px" id="loading_image">
                                        </c:if>
                                    </div>
                                </div>
                                <form:hidden path="id" id="buildingId"/>
                            </form>
                        </div>
                    </form:form>
                </div>
            </div><!-- /.page-content -->
        </div>
    </div><!-- /.main-content -->

</div><!-- /.main-container -->

<script>
    var imageBase64 = '';
    var imageName = '';
    $('#btnAddOrUpdateBuilding').click(function(){
        var data = {};
        var typeCode = [];
        var formData = $('#listForm').serializeArray();
        $.each(formData, function(i, v){
            if('' !== v.value && null != v.value){
                if (v.name != 'typeCode') {
                    data[""+v.name+""] = v.value;
                }
                else{
                    typeCode.push(v.value);
                }
            }
            if ('' !== imageBase64) {
                data['imageBase64'] = imageBase64;
                data['imageName'] = imageName;
            }

        });
        data['typeCode'] = typeCode;
        if (typeCode != '' && data.name != ''){
            $('#loading_image').show();
            addOrUpdateBuilding(data);
        }else{
            window.location.href = "<c:url value="/admin/building-edit?name=required&typeCode=required"/>";
        }

    });
    function addOrUpdateBuilding(data){
        $.ajax({
            type: "POST",
            url: "${buildingAPI}",
            data: JSON.stringify(data),
            contentType: "application/json",
            dataType: "JSON",
            success: function(response){
                $('#loading_image').hide();
                window.location.href = "<c:url  value ="/admin/building-edit?message=success"/>";
            },
            error: function(response){
                $('#loading_image').hide();
                window.location.href = "<c:url  value ="/admin/building-edit?message=error"/>";            }
        });
    }

    $('#btnCancel').click(function (){
        window.location.href = "<c:url value="/admin/building-list"/>";
    });

     $('#uploadImage').change(function (event) {
        var reader = new FileReader();
        var file = $(this)[0].files[0];
        reader.onload = function(e){
            imageBase64 = e.target.result;
            imageName = file.name; // ten hinh khong dau, khoang cach. vd: a-b-c
        };
        reader.readAsDataURL(file);
        openImage(this, "viewImage");
    });

    function openImage(input, imageView) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                $('#' +imageView).attr('src', reader.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
    }

</script>
</body>
</html>
