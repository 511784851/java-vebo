<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.blemobi.sep.probuf.nano.NewsProtos.PRecommendUser"%>

<!DOCTYPE html>
<html>
<head>
<title>迷雾科技 - 我的关注</title>
<%@ include file="include/head.jsp"%> 
</head>
<body>
	<%@ include file="include/menu.jsp"%> 

	<!--  PAPER WRAP -->
	<div class="wrap-fluid" style="margin-left:260px;">
		<div class="container-fluid paper-wrap bevel tlbr">
			<!-- BREADCRUMB -->
			<ul id="breadcrumb">
				<li><span class="entypo-home"></span></li>
				<li><i class="fa fa-lg fa-angle-right"></i></li>
				<li><a href="#" title="Sample page 1">我的关注</a></li>
			</ul>
			<br>
			<div class="content-wrap">
				<div class="row">
					<div class="col-sm-12">
						<div class="nest" id="FootableClose">

							<div class="body-nest" id="Footable">
								<table class="table-striped footable-res footable metro-blue" data-page-size="6">
									<thead>
										<tr>
											<th>用户</th>
											<th data-hide="phone,tablet">性别</th>
											<th data-hide="phone,tablet">会员级别</th>
											<th data-hide="phone">关系</th>
											<th data-hide="phone">是否为好友</th>
										</tr>
									</thead>
									<tbody>
										<%
											Object object = request.getAttribute("list");
											if (object != null) {
												PRecommendUser[] list = (PRecommendUser[]) object;
												for (PRecommendUser user : list) {
													String sex = "男";
													int gender = user.gender;
													if(gender==0){
														sex = "女";
													}
													String bool = "否";
													boolean friend = user.friend;
													if(friend){
														bool = "是";
													}
													
													String followshipStr = "无关系";
													int followship = user.followship;
													if(followship==1){
														followshipStr = "是粉丝";
													} else if(followship==2){
														followshipStr = "已关注";
													} else if(followship==3){
														followshipStr = "相互关注";
													}
										%><tr>
											<td>
											<img src="<%=user.headImgURL%>" style="width: 32px; height: 32px;border-radius:16px" align="middle"/>
											&nbsp;
											<%=user.username%></td>
											<td><%=sex%></td>
											<td><%=user.membershipLevel%></td>
											<td><%=followshipStr%></td>
											<td><%=bool%></td>
										</tr>
										<%
											}
											}
										%>
									</tbody>
								</table>
							</div>
						</div>
						<br>
						<div class="nest" id="FootableClose">
							<div class="body-nest" id="Footable">
								<button type="button" class="btn"
									onclick="javascript:location.href='/weibo/followers.do?count=<%=request.getAttribute("count")%>'">显示更多</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
