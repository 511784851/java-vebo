<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
<title>迷雾科技 - 微博发布系统</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<script type="text/javascript" src="/weibo/html/assets/js/jquery.min.js"></script>

<link rel="stylesheet" href="/weibo/html/assets/css/loader-style.css">
<link rel="stylesheet" href="/weibo/html/assets/css/bootstrap.css">
<link rel="stylesheet" href="/weibo/html/assets/css/signin.css">

<link rel="shortcut icon" href="/weibo/html/assets/ico/minus.png">
<style>
        #preview {
            width: 120px;
            height: 120px;
            overflow: hidden;
        }
        #preview img {
            width: 100%;
            height: 100%;
        }
    </style>
    <script src="../jquery/jquery-1.8.3.js"></script>
    <script type="text/javascript">
        function preview1(file) {
            var img = new Image(), url = img.src = URL.createObjectURL(file)
            var $img = $(img)
            img.onload = function() {
                URL.revokeObjectURL(url)
                $('#preview').empty().append($img)
            }
        }
        function preview2(file) {
            var reader = new FileReader()
            reader.onload = function(e) {
                var $img = $('<img>').attr("src", e.target.result)
                $('#preview').empty().append($img)
            }
            reader.readAsDataURL(file)
        }
         
        $(function() {
            $('[type=file]').change(function(e) {
                var file = e.target.files[0]
                preview2(file)
            })
        })
    </script>
</head>
<body>
<form enctype="multipart/form-data" action="" method="post">
    <input type="file" name="imageUpload"/>
    <div id="preview" style="border:1px solid gray;"></div>
</form>
</body>
</html>