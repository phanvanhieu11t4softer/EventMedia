<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!-- regiter group
 * vu.thi.tran.van@framgia.com
 * 26/05/2017
 -->

<body onload='getGroup();'>
	<section class="bg_white clearfix messageError">
		<div class="body clearfix mt20 hidden_elem" id="messageContainer">
			<spring:message code='init_group' text='' /> </div>
	</section>
	<section class="bg_white clearfix manageUser">
		<div class="body clearfix mt20">
			<div class="panel panel-default">
				<div class="panel-heading" style="height: 45px;">
					<div class="head-left" style="float: left; with: 75%">
						<h3 class="panel-title">Information group</h3>
					</div>
					<div class="head-right" style="float: right; with: 20%; margin-top: -4px;">
						<input type="button" id="btnEdit" value="Edit" class="btn btn-default">
					</div>
				</div>
				<div class="panel-body">
					<!-- Group Info -->
					<spring:url value="/manager/updateGroup" var="groupActionUrl" />
					<form:form id="EditGroupForm" class="form-horizontal" method="PUT"
						action="${groupActionUrl}" modelAttribute="group">
						<form:input path="id" name="id" id="id" class="hidden_elem" />
						<div class="form-group form-group-lg">
							<div class="col-sm-12">
								<div style="width: 12%; float: left;">
									<label>Name</label>
								</div>
								<div style="width: 88%; float: left;">
									<form:textarea path="name" name="name" id="name" class="form-control" style="display: inline; width: 100%;" disabled="true" />
								</div>
							</div>
							<div class="col-sm-12" style="padding: 5px"></div>
							<div class="col-sm-12">
								<div style="width: 12%; float: left;">
									<label>Description</label>
								</div>
								<div style="width: 88%; float: left;">
									<form:textarea path="description" name="description" id="description" class="form-control" style="display: inline; width: 100%;" disabled="true" />
								</div>
							</div>
							<div class="col-sm-12" style="padding: 5px"></div>
							<div class="col-sm-6">
								<div class="detail-borrowed-left">
									<label>Date start</label>
								</div>
								<div class="detail-borrowed-right">
									<form:input path="dateStart" name="dateStart" id="dateStart" class="form-control" style="display: inline; width: 100%;" disabled="true" />
								</div>
							</div>

							<div class="col-sm-6">
								<div class="detail-borrowed-left">
									<label>Date end</label>
								</div>
								<div class="detail-borrowed-right">
									<form:input path="dateEnd" name="dateEnd" id="dateEnd" class="form-control" style="display: inline; width: 100%;" disabled="true" />
								</div>
							</div>
							
							<div class="col-sm-12" style="padding: 5px"></div>
							<div class="col-sm-6">
								<div class="detail-borrowed-left">
									<label>Note</label>
								</div>
								<div class="detail-borrowed-right">
									<form:textarea path="note" name="note" id="note" class="form-control" style="display: inline; width: 100%;" disabled="true" />
								</div>
							</div>
							<div class="col-sm-3">
									<form:radiobutton path="status" name="status" id="active" value="0" disabled="true" />Active
									<span style="padding-left: 5px;"></span>
									<form:radiobutton path="status" name="status" id="inactive" value="1" disabled="true" />Inactive
							</div>
							<div class="col-sm-3">
									<form:radiobutton path="type" name="type" id="private" value="0" disabled="true" />Private
									<span style="padding-left: 5px;"></span>
									<form:radiobutton path="type" name="type" id="public" value="1" disabled="true" />Public

							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</section>

	<!-- List member -->
	<section class="bg_white clearfix manageUser">
		<div class="body clearfix mt20">
				<div class="panel panel-default">
					<div class="panel-heading">List Member</div>
					<!-- /.panel-heading -->
					<div class="panel-body">
						<div class="dataTable_wrapper">
							<table class="table table-striped table-bordered table-hover"
								id="dataTables-result" width="100%">
								<thead>
									<tr>
										<th>Username</th>
										<th>Name</th>
										<th>Email</th>
										<th>Sex</th>
										<th>Phone</th>
										<th>Birthday</th>
										<th>Remove</th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
						</div>
						<!-- /.table-responsive -->
					</div>
					<!-- /.panel-body -->
				</div>
				
			</div>
		<div class="clearfix"></div>
	</section>
	
	
	<!-- List image -->
	<section class="bg_white clearfix manageUser">
		<div class="body clearfix mt20">
				<div class="panel panel-default">
					<div class="panel-heading">List image</div>
					<!-- /.panel-heading -->
					<div class="panel-body">
						<div class="dataTable_wrapper">
							<table class="table table-striped table-bordered table-hover"
								id="dataTables-image" width="100%">
								<thead>
									<tr>
										<th>Tilte</th>
										<th>Author</th>
										<th>Number vote</th>
										<th>Remove</th>
										<th>View</th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
						</div>
						<!-- /.table-responsive -->
					</div>
					<!-- /.panel-body -->
				</div>
				
			</div>
		<div class="clearfix"></div>
	</section>

</body>