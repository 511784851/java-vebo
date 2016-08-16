<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.blemobi.sep.probuf.nano.NewsProtos.PPostInfo"%>
<%@ page import="com.blemobi.weibo.util.Base64"%>
<%@ page import="java.net.URLDecoder"%>
<%@ page import="java.net.URLEncoder"%>
<%@ page import="com.blemobi.weibo.util.CommonUtil"%>
<!DOCTYPE html>
<html>
<head>
<title>迷雾科技 - 微博系统</title>
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
				<li><a href="#" title="Sample page 1">全部微博</a></li>
			</ul>
			
			<div class="content-wrap">
                <div class="time-wrap">
                    <div class="row">
                        <div class="col-md-12">
                            <!-- The time line -->
                            <ul class="timeline">
								<%
									String startTime = CommonUtil.toSysDate(new Date());
									String endTime = "很久以前";
									long offset = 0;
								%>
                                <!-- timeline time label -->
                                <li class="time-label">
                                    <span class="bg-red">
                                        <%=startTime%>
                                    </span>
                                </li>
                                <!-- timeline item -->
								
								<%
									Object object = request.getAttribute("list");
									Object uuid = request.getAttribute("uuid");
									if (object != null) {
										PPostInfo[] articleList = (PPostInfo[]) object;
										for (PPostInfo article : articleList) {
											//System.out.println(article);
											String pubishTime = CommonUtil.toUnixDate(article.pubishTime);
											endTime = CommonUtil.toSysDate(article.pubishTime);
											offset = article.id;
											String content = article.content;
											if(content!=null&&content.toString().length()>200){
												content = content.toString().substring(0,200)+"...";
											}
								%>
								 <li>
                                    <i class="fa fa-envelope bg-time"></i>
                                    <div class="timeline-item">
                                        <span class="time">
											<ul class="list-inline">
												<li>
												<%
													if(!article.collected){
														%>
														<a href="/weibo/collect.do?id=<%=article.id%>&puuid=<%=article.uuid%>&path=list">收藏</a>
														<%
													} else {
														%>
														<a href="/weibo/uncollect.do?id=<%=article.id%>&puuid=<%=article.uuid%>&path=list">取消收藏</a>
														<%
													}
												%>
												&nbsp;&nbsp;<a href="/weibo/delete.do?id=<%=article.id%>&path=list">删除</a>
												</li>
												&nbsp;&nbsp;&nbsp;&nbsp;
												</li>
												<li><span class="entypo-network"></span>&nbsp;
														收藏 <%=article.collectCnt%>&nbsp;&nbsp;&nbsp;&nbsp;
												</li>
												<li><span class="entypo-chat"></span>&nbsp;
														评论 <%=article.commentCnt%>&nbsp;&nbsp;&nbsp;&nbsp;
												</li>
												<li><span class="entypo-share"></span>&nbsp;
														点赞 <%=article.voteCnt%>
												</li>
											</ul>
										</span>
                                        <h3 class="timeline-header"> <%=pubishTime%></h3>
                                        <div class="timeline-body">
										<a href="/weibo/detail.do?id=<%=article.id%>"><%=content%>&nbsp;&nbsp;
										<%
										String srcType = article.srcType;
											if ("image".equals(srcType)) {// 图片微博
										%> <span class="status-metro status-disabled" title="图片">图片</span>
										<%
											} else if ("video".equals(srcType)) {// 视频微博
										%> <span class="status-metro status-suspended" title="视频">视频</span>
										<%
											} else if ("audio".equals(srcType)) {// 音频微博
										%> <span class="status-metro status-active" title="音频">音频</span>
										<%
											}
										%>
										</a>
										   </div>
                                    </div>
                                </li>
								
							
								<%
									}
									}
								%>

                                <!-- timeline time label -->
                                <li class="time-label">
                                    <span class="bg-green">
                                        <%=endTime%>
                                    </span>
                                </li>
                                <!-- /.timeline-label -->
								<li>
                                    <i class="fa fa-camera bg-time"></i>
                                    <div class="timeline-item">
									 <%
											String isNext = request.getAttribute("isNext").toString();
											if(offset==0){
												%>
												没有更多数据了
												<%
												if("1".equals(isNext)){
													%>
													<a class="timeline-header" href='/weibo/list.do?isNext=0&offset=<%=offset%>'>返回首页</a>
													<%
												}
												%>
												<%
											} else {
												%>
												<a class="timeline-header" href='/weibo/list.do?isNext=1&offset=<%=offset%>'>下一页</a>
												<%
											}
										%>
                                    </div>
                                </li>
                                <li>
                                    <i class="fa fa-clock-o bg-time"></i>
                                </li>
                            </ul>
                        </div>
                        <!-- /.col -->
                    </div>
                    <!-- /.row -->
                </div>
            </div>
		</div>
	</div>
</body>
</html>
