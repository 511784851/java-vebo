<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
<title>迷雾科技 - 微博系统</title>
<%@ include file="include/head.jsp"%> 
<style>
        .preview {
            width: 153px;
            height: 153px;
        }
        .preview img {
            width: 100%;
            height: 100%;
        }
    </style>
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
				<li><a href="#" title="Sample page 1">发布微博</a></li>
			</ul>

			<!-- END OF BREADCRUMB -->
			<div class="content-wrap">
				<div class="row">
					<div class="col-sm-12">
						<div class="nest" id="elementClose">
							<div class="body-nest" id="element">
								<div class="panel-body">
									<form action="/weibo/publish.do" method="post"
										enctype="multipart/form-data" class="form-horizontal bucket-form">
										<div class="form-group">
											<label class="col-sm-3 control-label">类型</label>
											<div class="col-sm-6">
												<input type="radio" name="srcType" value="text"
													onclick="selectSrcType()">
												文字微博&nbsp;&nbsp;&nbsp;&nbsp; <input type="radio"
													name="srcType" value="image" onclick="selectSrcType()"
													checked> 图片微博&nbsp;&nbsp;&nbsp;&nbsp; <input
													type="radio" name="srcType" value="video"
													onclick="selectSrcType()">
												视频微博&nbsp;&nbsp;&nbsp;&nbsp; <input type="radio"
													name="srcType" value="audio" onclick="selectSrcType()">
												音频微博
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-3 control-label">正文</label>
											<div class="col-sm-6">
												<textarea name="content" style="width:479px;height:100px;"></textarea>
											</div>
										</div>
										<div class="form-group" id="imageFileId">
											<label class="col-sm-3 control-label">图片</label>
											<div class="col-sm-6" style="width:600px;">
												<%
													for (int i = 0; i < 9; i++) {
												%>
												<div id="drag-and-drop-zone" class="uploader"
													style="width:153px;float:left;margin-right:10px;margin-button:10px;padding:0px;">
													<div id="preview<%=i%>" class="preview"><br><br><br>图片</div>
													<div class="browser" style="margin-top:0px;">
														<label style="width:153px;"> <span>选择图片</span> <input
															 id="upload_image<%=i%>" type="file" name="imageFile" multiple="multiple">
														</label>
													</div>
												</div>
												<%
													}
												%>
											</div>
										</div>
										<div class="form-group" id="videoFileId" style="display:none;">
											<label class="col-sm-3 control-label">视频</label>
											<div class="col-sm-6">
												<div id="drag-and-drop-zone" class="uploader"
													style="width:479px;height:310px;float:left;margin-right:20px;">
													<div class="or" style="height:80px;"></div>
													<div style="height:120px;">视频</div>
													<div class="browser">
														<label style="width:479px;"> <span>选择视频</span> <input
															type="file" name="videoFile" multiple="multiple">
														</label>
													</div>
												</div>
											</div>
										</div>
										<div class="form-group" id="audioFileId" style="display:none;">
											<label class="col-sm-3 control-label">音频</label>
											<div class="col-sm-6">
												<div id="drag-and-drop-zone" class="uploader"
													style="width:479px;height:160px;float:left;margin-right:20px;">
													<div class="or" style="height:20px;"></div>
													<div style="height:40px;">音频</div>
													<div class="browser">
														<label style="width:479px;"> <span>选择音频</span> <input
															type="file" name="audioFile" multiple="multiple">
														</label>
													</div>
												</div>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-3 control-label"></label>
											<div class="col-sm-6">
												<div class="form-group">
													<label class="col-sm-3 control-label"></label>
													<div class="col-sm-6">
														<button type="submit" style="margin-top:10px;"
															class="btn btn-info" id="submit-all">&nbsp;&nbsp;&nbsp;&nbsp;发布微博&nbsp;&nbsp;&nbsp;&nbsp;</button>
													</div>
												</div>
											</div>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	 <input type="file" name="imageUpload" id="imageUpload"/>
    
</body>
</html>
<script type="text/javascript">
	function selectSrcType() {
		var srcType = $('input[name="srcType"]:checked').val();
		if (srcType == "image") {
			$("#imageFileId").show();
			$("#videoFileId").hide();
			$("#audioFileId").hide();
		} else if (srcType == "video") {
			$("#imageFileId").hide();
			$("#videoFileId").show();
			$("#audioFileId").hide();
		} else if (srcType == "audio") {
			$("#imageFileId").hide();
			$("#videoFileId").hide();
			$("#audioFileId").show();
		} else {
			$("#imageFileId").hide();
			$("#videoFileId").hide();
			$("#audioFileId").hide();
		}
	}
</script>

<script type="text/javascript">
	function preview(file,i) {
		var img = new Image(), url = img.src = URL.createObjectURL(file)
		var $img = $(img)
		img.onload = function() {
			URL.revokeObjectURL(url)
			$('#preview'+i).empty().append($img)
		}
	}
	
	function change(i) {
		$('#upload_image'+i).change(function(e) {
			var file = e.target.files[0]
			preview(file,i)
		})
	}
	
	$(function() {
		for(var i=0;i<9;i++){
			change(i);
		}
	})
</script>