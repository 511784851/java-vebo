<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.blemobi.sep.probuf.nano.NewsProtos.PPostInfo"%>
<%@ page import="com.blemobi.sep.probuf.nano.NewsProtos.PImage"%>
<%@ page import="com.blemobi.sep.probuf.nano.NewsProtos.PVideo"%>
<%@ page import="com.blemobi.sep.probuf.nano.NewsProtos.PAudio"%>

<%@ page import="com.blemobi.sep.probuf.nano.CommentProtos.PComment"%>
<%@ page import="com.blemobi.sep.probuf.nano.CommentProtos.PCommentLevel"%>
<%@ page import="com.blemobi.sep.probuf.nano.CommentProtos.PCommentList"%>

<%@ page import="com.blemobi.sep.probuf.nano.NewsProtos.PAtUser"%>
<%@ page import="com.blemobi.sep.probuf.nano.NewsProtos.PAtUserList"%>

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
				<li><a href="#" title="Sample page 1">微博内容</a></li>
			</ul>
			<!--CONTENT-->
			<div class="row" style="margin-top:30px;padding:0 15px;">
				<!--  BLOG CONTENT -->
				<div class="col-sm-8">
					<%
						Object object = request.getAttribute("article");
						PPostInfo article = (PPostInfo) object;
						String srcType = article.srcType;
						String pubishTime = CommonUtil.toUnixDate(article.pubishTime);
						String srcTypeVal = "";
						if ("image".equals(srcType)) {// 图片微博
							srcTypeVal = "图片";
						} else if ("video".equals(srcType)) {// 视频微博
							srcTypeVal = "视频";
						} else if ("audio".equals(srcType)) {// 音频微博
							srcTypeVal = "音频";
						} else if ("text".equals(srcType)) {
							srcTypeVal = "文字";
						}
					%>
					<div class="blog-list-nest" style="height:auto;">
						<div class="blog-list-content">
							<h2><%=pubishTime%></h2>
							<p><%=article.content%></p>

							<%
								if ("image".equals(srcType)) {// 图片微博
							%><div style="width:500px;">
								<%
									PImage[] imgList = article.images;
										for (PImage image : imgList) {
								%>
								<img src="<%=image.url%>"
									style="width:120px;height:120px;margin-right:5px;margin-bottom:5px;" />
								<%
									}
								%>
							</div>
							<%
								} else if ("video".equals(srcType)) {// 视频微博
									PVideo video = article.video;
							%>
							<video src="<%=video.url%>"
								style="width:479px;height:310px;" controls="controls">
								浏览器不支持
							</video>
							<%
								} else if ("audio".equals(srcType)) {// 音频微博
									PAudio audio = article.audio;
							%>
							<audio src="<%=audio.url%>" controls autoplay loop
								style="width:479px;"> 浏览器不支持
							</audio>
							<%
								}
							%>
							<hr>
							<ul class="list-inline">
								<li><span class="entypo-network"></span>&nbsp;
										收藏 <%=article.collectCnt%>&nbsp;&nbsp;&nbsp;&nbsp;
								</li>
								<li><span class="entypo-chat"></span>&nbsp;
										评论 <%=article.commentCnt%>&nbsp;&nbsp;&nbsp;&nbsp;
								</li>
								<li><span class="entypo-share"></span>&nbsp;
										点赞 <%=article.voteCnt%>
								</li>
								<li>&nbsp;&nbsp;&nbsp;&nbsp;<a href="/weibo/delete.do?id=<%=article.id%>&path=detail">删除</a>&nbsp;&nbsp;
								<%
									if(!article.collected){
										%>
										<a href="/weibo/collect.do?id=<%=article.id%>&path=detail">收藏</a>
										<%
									} else {
										%>
										<a href="/weibo/uncollect.do?id=<%=article.id%>&path=detail">取消收藏</a>
										<%
									}
								%>
								</li>
							</ul>
						</div>
					</div>
					<!-- 这是评论 -->
					<div class="comment-nest">
					评论内容
					<%
						String ss = "还没有评论...";
						Object commentListObject = request.getAttribute("commentList");
						if(commentListObject!=null){
							PComment[] commentList = (PComment[]) commentListObject;
							for(PComment comment : commentList){
								ss = "";
								String createTime = CommonUtil.toUnixDate(comment.createTime);
						%>
						<hr>
						<ul class="media-list">
							<li class="media"><a class="pull-left" href="#"> <img
									class="media-object img-circle" data-src="holder.js/64x64"
									alt="64x64"
									src="<%=comment.headImgUrl%>"
									style="width: 64px; height: 64px;">
							</a>
								<div class="media-body">
									<div class="social-profile">
										<h3>
											<a class="link-comment" href="#"><%=comment.nickname%></a> <span><i
												class="entypo-globe"></i>&nbsp;<%=createTime%></span>
										</h3>
									</div>

									<p><%=comment.text%></p>
								</div>
							</li>
						</ul>
						<%
							}
						} 
						if(!"".equals(ss)) {
							%><hr>				
							<ul class="media-list">
								<%=ss%>
							
							</ul>
							<%
						}
					%>
					</div>
				</div>
				
				 <!--  BLOG SIDE CONTENT -->
                <div class="col-sm-4">
                    <div class="blog-side-nest">
                        <h4 class="text-left">@用户</h4>
                        <hr style="margin:0">

						<%
							PAtUserList atusers = article.atusers;
							if(atusers!=null){
								PAtUser[] atuserList = atusers.list;
								for(PAtUser user:atuserList){
									%>
									<div class="media">
										<a class="pull-left" href="#">
											<img class="media-object img-responsive-media" data-src="holder.js/64x64" alt="64x64" src="">
										</a>
										<div class="media-body">
											<h5 class="media-heading"> <%=user.nickName%>  </h5>
											<a class="link-comment recentpost-link" style="margin:-10px 0 10px" href="#">88 Views</a>
										</div>
									</div>
									<%
								}
							} else {
								%>
									<div class="media">
										还没有@用户...
									</div>
									<%
							}
						%>
                        

                        <br>
                    </div>

                </div>
                <!--  END OF BLOG SIDE CONTENT -->
           
			</div>
		</div>
	</div>
	<!-- END OF RIGHT SLIDER CONTENT-->
</body>
</html>
