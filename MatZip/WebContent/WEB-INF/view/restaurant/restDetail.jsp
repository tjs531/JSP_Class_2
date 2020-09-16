<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div>
	<div class="recMenuContainer">
		<c:forEach items="${recommendMenuList}" var="item">
			<div class="recMenuItem" id="recMenuItem_${item.seq}">
				<div class="pic">
					<c:if test="${item.menu_pic != null and item.menu_pic != ''}">
						<img src="/res/img/restaurant/${data.i_rest}/${item.menu_pic}">
					</c:if>
				</div>
				<div class="info">
					<div class="nm">${item.menu_nm}</div>
					<div class="price"><fmt:formatNumber type="number" value="${item.menu_price}"/>��</div>
				</div>
				<c:if test="${loginUser.i_user == data.i_user}">
					<div class="delIconContainer" onclick="delRecMenu(${item.seq})">
						<span class="material-icons">clear</span>
					</div>
				</c:if>
			</div>
		</c:forEach>
	</div>
	<div id="sectionContainerCenter">
		<div>
			<c:if test="${loginUser.i_user == data.i_user}">
				<button onclick="isDel()">���� ����</button>
				
				<h2>- ��õ �޴� -</h2>
				<div>
					<div><button type="button" onclick="addRecMenu()">��õ �޴� �߰�</button></div>
					<form id="recFrm" action="/restaurant/addRecMenusProc" enctype="multipart/form-data" method="post">
						<input type="hidden" name="i_rest" value="${data.i_rest}">
						<div id="recItem"></div>
						<div><input type="submit" value="���"></div>
					</form>
				</div>
				
				<h2>- �޴� -</h2>
				<div>
					<form id="menuFrm" action="/restaurant/addMenusProc" enctype="multipart/form-data" method="post">
						<input type="hidden" name="i_rest" value="${data.i_rest}">
						<input type="file" name="menu_pic" multiple>
						<div><input type="submit" value="���"></div>
					</form>
				</div>
			</c:if>
			
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
							<tr>
								<th>�޴�</th>
								<td>	
									<div class="menuList">
										<c:forEach var="i" begin="0" end="${fn:length(menuList) > 3 ? 2 : fn:length(menuList)}">
											<div class="menuItem">
												<img src="/res/img/restaurant/${data.i_rest}/menu/${menuList[i].menu_pic}">
											</div>
										</c:forEach>
										<c:if test="${fn:length(menuList) > 3}">
											<div class="menuItem bg_black">
												<div class="moreCnt">
													+${fn:length(menuList) - 3}
												</div>
											</div>
										</c:if>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script>
	function delRecMenu(seq) {
		if(!confirm('�����Ͻðڽ��ϱ�?')) {
			return
		}	
		console.log('seq : ' + seq)
		
		axios.get('/restaurant/ajaxDelRecMenu', {
			params: {
				i_rest: ${data.i_rest},
				seq: seq
			}
		}).then(function(res) {
			console.log(res)
			if(res.data == 1) {
				//������Ʈ ����
				var ele = document.querySelector('#recMenuItem_' + seq)
				ele.remove()
			}
		})
	}
	var idx = 0;
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
		inputPic.setAttribute('name', 'menu_pic_' + idx++)
		
		div.append('�޴�: ')
		div.append(inputNm)
		div.append(' ����: ')
		div.append(inputPrice)
		div.append(' ����: ')
		div.append(inputPic)
		
		recItem.append(div)
	}
	addRecMenu()
	function isDel() {
		if(confirm('���� �Ͻðڽ��ϱ�?')) {
			location.href = '/restaurant/restDel?i_rest=${data.i_rest}'
		}
	}
</script>