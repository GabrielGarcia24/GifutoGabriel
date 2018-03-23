            <div class="left-sidebar">
						<h2>Categorías</h2>
						<div class="panel-group category-products" id="accordian"><!--category-productsr-->
                                                    <%! 
                                                         int id;
                                                    %>
                                                    <%
                                                         for (Categoria catSup:CategoriaDAO.listar()) {
                                                             id=catSup.getId();
                                                    %>
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title">
                                                                            <a <% if(CategoriaDAO.esSuperior(id)){ %>data-toggle="collapse" data-parent="#accordian"<% } %> href="#<%=id%>">
											<% if(CategoriaDAO.esSuperior(id)){ %><span class="badge pull-right"><i class="fa fa-plus"></i></span><% } %>         
                                                                                        <a href="?category=<%=id%>"><%=catSup.getNombre()%></a>
										</a>
									</h4>
								</div>
								<div id="<%=id%>" class="panel-collapse collapse">
									<div class="panel-body">
										<ul>
                                                                                    <% ArrayList<Categoria> catSub = CategoriaDAO.listarSubCategorias(id);
                                                                                          for (Categoria sub : catSub) {
                                                                                                  
                                                                                    %>
											<li><a href="?category=<%=id%>"><%=sub.getNombre()%></a></li>
                                                                                         
										    <% } %>
										</ul>
									</div>
								</div>
							</div>
						<% } %>
						</div><!--/category-products-->
					
						
						<div class="shipping text-center"><!--shipping-->
							<img src="images/home/shipping.jpg" alt="" />
						</div><!--/shipping-->
					
					</div>