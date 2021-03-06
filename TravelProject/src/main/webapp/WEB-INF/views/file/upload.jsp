<%@ page language="java" 
    contentType="text/html;charset=utf-8"
    pageEncoding="utf-8"%>
<%@include file="../include/common_main.jsp"%>    
<!doctype html>
<html lang="ko">
<head>
<meta charset="utf-8">
<title>jQuery(제이쿼리) 강좌와 예제모음 : 요소 동적추가하기와 복사하기에 대한 이야기</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="http://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>
<body>

<div style="text-align:center; margin:0 0 100px 0; background:#555; line-height:80px;">
	<a style="color:#fff; font-weight:bold; font-family:tahoma; font-size:20pt;" href="http://ktsmemo.cafe24.com/s/jquery/82" target="_blank">http://ktsmemo.cafe24.com/s/jquery/82</a>
</div>


<!-- 예제 시작 -->
 
<table id="preset" style="display:none;">
	<tr name="tr_attach_file">
		<th>첨부파일</th>
		<td>
			<input type="file" name="file" />
			<!-- <input type="title" name="title" id="title" />  -->
		</td>
	</tr>
</table>
 
<form name="mform" id="mform" method="post" enctype="multipart/form-data">  
<input type="text" name="userid" id="userid" value="${userid }"/>
<table  cellpadding="5" cellspacing="0" border="1">
	<tr>
		<th>제목</th>
		<td>.<!-- <input type="title" name="title" id="title" /> --></td>
	</tr>
	<tr>
		<th>내용</th>
		<td>.</td>
	</tr>
	<tr name="tr_attach_file">
		<th>첨부파일</th>
		<td>
			<input type="file" name="file" id="file" class="atchfile"/> <button type="button" onclick="addFile()">추가</button>
			<!-- <input type="file" name="file" id="file" class="atchfile"/> <button type="button" onclick="addFile()">추가f</button> -->
		</td>
	</tr>
	<!-- 추가 버튼을 누르면 위 숨겨진 테이블의 tr 을 가져다가 추가할 겁니닷 -->
</table>  

<button type="text" name="btnWrite" id="btnWrite">전송하기</button>
</form>
<script>

	function addFile(){
		$('[name=mform] [name=tr_attach_file]:last').after( $('#preset tr').clone() );
		// $('#preset tr').clone() : id=preset 에서 tr 요소 셀렉트 하여 복제 !!
		// form1 안에 이름이 tr_attach_file 인것 중 마지막 것 다음에 추가합니다.
		// 이렇게 하면 실행때 마다 동적으로 하나씩 추가됩니다.
	}
	
	
	
	
	$(document).ready(function(){
		
		$("#btnWrite").click(function(event){
			//에러체크 (제목, 내용, 아이디)
			
			event.preventDefault();
			//데이타 전송하기 위해서 form에 있는
			//모든 값들을 parameter 화한다 
			var form = $("#mform")[0];
			
			var formData = new FormData(form);
			
			//alert(form.attachFile);
			/*
			첨부된 파일 수정시 어려운 이유?
			1. 삭제만 누르고, 파일 첨부 안하는 경우 
			2. 파일을 전부 또는 차례대로 삭제 안하고 
			   부분 부분 삭제를 한다 
			   배열로 넘어가서, 실제 삭제 된 대상이 
			   누군지 알아 낸 다음 
			   업데이트 될 필드가 뭔지 정확하게 알
			   아야 한다 
			   
			   
			*/

			// title=제목1&contents=내용1
			console.log(formData.getAll("file").length);
			//return false;
			
			//파일의 경우 반드시 추가로 뭔가를 해줘야 한다 
						     
			var url="${commonURL}/image/save.do";
		    console.log(url);
		    alert(url);
			//첨부파일 처리 
			$.ajax({
				url:url,
				processData:false, //fileupload시 꼭 필요
				contentType:false, //fileupload시 꼭 필요
				data:formData, 
				enctype:"multipart/form-data",
				timeout:600000,
				dataType: 'text',
				type:'POST',  //fileupload시 꼭 post
				success:function(result){
					//서버호출 성공시 
						alert('사진이 등록되었습니다');
					
					location.href="<%=commonURL%>/board/albumbbs.do";
				},
				error:function(request, status, error){
					//서버호출 실패시 
					alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
					console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
					//console.log(status);
					//console.log(request);
					//console.log(error);
					alert("파일 용량이 너무 커서 못올립니다");
					
				}
			});
			
			
		});
	});


</script>

<!-- 예제 종료 -->





</body>
</html>