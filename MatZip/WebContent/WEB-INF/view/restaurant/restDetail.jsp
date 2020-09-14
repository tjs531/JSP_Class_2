<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
 <div id="sectionContainerCenter">
	<div>
		<c:if test="${loginUser.i_user == data.i_user}">
		<div>			
			<button onclick="isDel()">����</button>
			
			<form id="recFrm" action="/restaurant/addRecMenusProc" enctype="multipart/form-data" method="post">
				<div><button type="button" onclick="addRecMenu()">�޴� �߰�</button></div>
				<input type="hidden" name="i_rest" value="${data.i_rest}">
				<div id="recItem">
					<div>
						�޴�: <input type="text" name="menu_nm"> 
						����: <input type="number" name="menu_price">
						����: <input type="file" name="menu_pic">
					</div>
				</div>
				<div><input type="submit" value="���"></div>
			</form>
		</div>
		</c:if>
		<div>
			���� ������
		</div>
		<div class="restaurant-detail">
			<div id="detail-header">
				<div class="restaurant_title_wrap">
					<span class="title">
						<h1 class="restaurant_name">${data.nm}</h1>						
					</span>
				</div>
				<div class="status branch_none">
					<span class="cnt hit">${data.cntHits}</span>					
					<span class="cnt favorite">${data.cntFavorite}</span>
				</div>
			</div>
			<div>
				<table>
					<caption>������� �� ����</caption>
					<tbody>
						<tr>
							<th>�ּ�</th>
							<td>${data.addr}</td>
						</tr>
						<tr>
							<th>ī�װ�</th>
							<td>${data.cd_category_nm}</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	
	<script>
		function addRecMenu() {
			var div = document.createElement('div')
			
			var inputNm = document.createElement('input')
			inputNm.setAttribute('type', 'text')
			inputNm.setAttribute('name', 'menu_nm')
			var inputPrice = document.createElement('input')
			inputPrice.setAttribute('type', 'number')
			inputPrice.setAttribute('name', 'menu_price')
			var inputPic = document.createElement('input')
			inputPic.setAttribute('type', 'file')
			inputPic.setAttribute('name', 'menu_pic')
			
			div.append('�޴�: ')
			div.append(inputNm)
			div.append(' ����: ')
			div.append(inputPrice)
			div.append(' ����: ')
			div.append(inputPic)
			
			recItem.append(div)
		}
	
		function isDel() {
			if(confirm('���� �Ͻðڽ��ϱ�?')) {
				location.href = '/restaurant/restDel?i_rest=${data.i_rest}'
			}
		}
	</script>
</div>