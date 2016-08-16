<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.blemobi.sep.probuf.nano.NewsProtos.PPersonStatistic"%>

<!DOCTYPE html>
<html>
<head>
<title>迷雾科技 - 微博系统</title>
<%@ include file="include/head.jsp"%> 
</head>
<body>
	<%@ include file="include/menu.jsp"%> 
	<%
		Object object = request.getAttribute("person");
		PPersonStatistic person = (PPersonStatistic) object;
	%>
	<!--  PAPER WRAP -->
	<div class="wrap-fluid" style="margin-left:260px;">
		<div class="container-fluid paper-wrap bevel tlbr">
			<!-- BREADCRUMB -->
			<ul id="breadcrumb">
				<li><span class="entypo-home"></span></li>
				<li><i class="fa fa-lg fa-angle-right"></i></li>
				<li><a href="#" title="Sample page 1">统计信息</a></li>
			</ul>
			<!--  DEVICE MANAGER -->
            <div class="content-wrap">
                <div class="row">
					<br>
                    <div class="col-lg-3">
                        <div class="profit" id="profitClose">
                            <div class="headline ">
                                <h3>
                                    <span>朋友</span>
                                </h3>
                            </div>
							<div class="value">
                               <%=person.friendCnt%><b>朋友</b>
                            </div>
                            <div class="progress-tinny">
                                <div style="width: 50%" class="bar"></div>
                            </div>
                            <div class="profit-line">&nbsp;</div>
                        </div>
                    </div>
                    <div class="col-lg-3">
                        <div class="order" id="orderClose">
                            <div class="headline ">
                                <h3>
                                    <span>关注</span>
                                </h3>                             
                            </div>
                            <div class="value">
                               <%=person.followCnt%><b>关注</b>
                            </div>
                            <div class="progress-tinny">
                                <div style="width: 50%" class="bar"></div>
                            </div>
                            <div class="profit-line">&nbsp;</div>
                        </div>
                    </div>
                    <div class="col-lg-3">
                        <div class=" member" id="memberClose">
                            <div class="headline ">
                                <h3>
                                    <span>粉丝
                                    </span>
                                </h3>
                             
                            </div>
                            <div class="value">
                                <%=person.fansCnt%><b>粉丝</b>
                            </div>
                            <div class="progress-tinny">
                                <div style="width: 50%" class="bar"></div>
                            </div>
                            <div class="profit-line">&nbsp;</div>
                        </div>
                    </div>
					
                    <div class="col-lg-3">
                        <div class="profit" id="profitClose">
                            <div class="headline ">
                                <h3>
                                    <span>微博</span>
                                </h3>
                            </div>
							<div class="value">
                               <%=(person.textCnt+person.imageCnt+person.videoCnt+person.audioCnt)%><b>微博</b>
                            </div>
                            <div class="progress-tinny">
                                <div style="width: 50%" class="bar"></div>
                            </div>
                            <div class="profit-line">&nbsp;</div>
                        </div>
                    </div>
					<div class="col-lg-3">
                        <div class="order" id="orderClose">
                            <div class="headline ">
                                <h3>
                                    <span>文字微博</span>
                                </h3>                             
                            </div>
                            <div class="value">
                               <%=person.textCnt%>
                            </div>
                            <div class="progress-tinny">
                                <div style="width: 50%" class="bar"></div>
                            </div>
                            <div class="profit-line">&nbsp;</div>
                        </div>
                    </div>
                    <div class="col-lg-3">
                        <div class="order" id="orderClose">
                            <div class="headline ">
                                <h3>
                                    <span>图片微博</span>
                                </h3>                             
                            </div>
                            <div class="value">
                               <%=person.imageCnt%>
                            </div>
                            <div class="progress-tinny">
                                <div style="width: 50%" class="bar"></div>
                            </div>
                            <div class="profit-line">&nbsp;</div>
                        </div>
                    </div>
                    <div class="col-lg-3">
                        <div class=" member" id="memberClose">
                            <div class="headline ">
                                <h3>
                                    <span>视频微博
                                    </span>
                                </h3>
                             
                            </div>
                            <div class="value">
                                <%=person.videoCnt%>
                            </div>
                            <div class="progress-tinny">
                                <div style="width: 50%" class="bar"></div>
                            </div>
                            <div class="profit-line">&nbsp;</div>
                        </div>
                    </div>
					<div class="col-lg-3">
                        <div class=" member" id="memberClose">
                            <div class="headline ">
                                <h3>
                                    <span>音频微博
                                    </span>
                                </h3>
                             
                            </div>
                            <div class="value">
                                <%=person.audioCnt%>
                            </div>
                            <div class="progress-tinny">
                                <div style="width: 50%" class="bar"></div>
                            </div>
                            <div class="profit-line">&nbsp;</div>
                        </div>
                    </div>
                </div>
            </div>
		</div>
	</div>
	<!-- END OF RIGHT SLIDER CONTENT-->
</body>
</html>