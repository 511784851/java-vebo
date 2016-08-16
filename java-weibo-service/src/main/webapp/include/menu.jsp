<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
	<!-- TOP NAVBAR -->
	<nav role="navigation" class="navbar navbar-static-top">
		<div class="container-fluid">
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div id="bs-example-navbar-collapse-1"
				class="collapse navbar-collapse">
				<ul style="margin-right:0;" class="nav navbar-nav navbar-right">
					<li id="infoId"></li>
					<li><a data-toggle="dropdown" class="dropdown-toggle"
						href="/weibo/logout.do"> &#160;&#160;退出</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<!-- SIDE MENU -->
	<div id="skin-select">
		<div id="logo">
			<h1>
				迷雾科技<span>微博系统</span>
			</h1>
		</div>

		<a id="toggle"> <span class="entypo-menu"></span>
		</a>

		<div class="skin-part">
			<div id="tree-wrap">
				<div class="side-bar">
					<ul class="topnav menu-left-nest">
						<li><a class="tooltip-tip2 ajax-load"
							href="/weibo/home.do" title="Social"> <i
								class="icon-graph-pie"></i> <span>统计信息</span>
						</a></li>
					</ul>
					<br>
					<ul class="topnav menu-left-nest">
					
						<li>
						 <a class="tooltip-tip ajax-load" href="/weibo/publish.jsp" title="Blog App">
                                <i class="icon-document-edit"></i>
                                <span>发布微博</span>
                            </a>
						</li>
						<li>
						 <a class="ttooltip-tip2 ajax-load" href="/weibo/list.do?isNext=0&offset=0" title="Blog App">
                                <i class="icon-view-list"></i>
                                <span>全部微博</span>
                            </a>
						</li>
						<li>
						 <a class="ttooltip-tip2 ajax-load" href="/weibo/listcollect.do?isNext=0&offset=0" title="Blog App">
                                <i class="icon-view-list"></i>
                                <span>收藏微博</span>
                            </a>
						</li>	
					</ul>
					<br>
					<ul class="topnav menu-left-nest">
						<li><a class="tooltip-tip2 ajax-load"
							href="/weibo/fans.do?count=20" title="Profile Page"><i
								class="icon-user">
								</i><span>我的粉丝</span>
						</a></li>
						<li><a class="tooltip-tip2 ajax-load"
							href="/weibo/followers.do?count=20" title="Profile Page"><i
								class="icon-user">
								</i> <span>我的关注</span>
						</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<%
		Object msgObject = request.getAttribute("msgCode");
		String msgCode = "";
		if(msgObject!=null){
			msgCode = msgObject.toString();
		}
	%>
	
	<script type="text/javascript">
		var nickname = decodeURI($.cookie("nickname"));
		var imgURL =   decodeURI($.cookie("imgURL"));

		var hs = "<a data-toggle='dropdown' class='dropdown-toggle' href='#'>"
				+ "<img alt='' class='admin-pic img-circle' src='"+imgURL+"'/>Hi,"
				+ nickname + "</a>";

		$("#infoId").append(hs);
	</script>
	
	<script type="text/javascript">
	var msgCode = '<%=msgCode%>';
	if(msgCode=="11000"){
		alert("发布微博失败");
	} else if(msgCode=="11001"){
		alert("删除微博失败");
	} else if(msgCode=="11002"){
		alert("收藏微博失败");
	} else if(msgCode=="11003"){
		alert("取消收藏微博失败");
	}
	</script>