<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="EUC-KR">
            <title>Insert title here</title>
            <script src="https://code.jquery.com/jquery-3.6.1.min.js"
                integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous">
                </script>
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet"
                integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT"
                crossorigin="anonymous">
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8"
                crossorigin="anonymous"></script>

            <style>
                body {
                    background-color: rgb(22, 22, 22);
                }

                #title {
                    color: red;
                    font-size: 80px;
                    font-weight: bolder;
                }

                .form-control {
                    background-color: rgb(22, 22, 22);
                    color:white;
                }

                .table {
                    color: white;
                }

                #list {
                    overflow-y: scroll;
                    max-height: 500px;
                }
            </style>
        </head>

        <body>
            <form action="index.jsp" style="position:fixed; top:1%; left:3%">
                <button class="btn btn-dark text-center">Main menu</button>
            </form>
            <div class="container">
                <div class="header">
                    <div class="row">
                        <span class="col-12 text-center m-5" id="title">Netflix</span>
                    </div>
                </div>
                <div class="menu">
                    <div>
                        <!-- Delete -->
                        <form action="delete.movie" class="row justify-content-center mt-5">
                            <div class="col-5 text-white">
                                <input type="text" class="form-control" placeholder="ID of movie for delete" name="id">
                            </div>
                            <div class="col-1">
                                <button class="btn btn-dark text-center">Delete</button>
                            </div>
                        </form>
                    </div>
                    <div>
                        <!-- Modify -->
                        <form action="update.movie" class="row justify-content-center mt-2">
                            <div class="col-5 text-white">
                                <input type="text" class="form-control" placeholder="ID of movie for modify" name="id">
                                <input type="text" class="form-control" placeholder="change title" name="title">
                                <input type="text" class="form-control" placeholder="change category" name="genre">
                            </div>
                            <div class="col-1">
                                <button class="btn btn-dark text-center">Modify</button>
                            </div>
                        </form>
                    </div>
                </div>
                <div>
                    <!-- Search -->
                    <form action="searchTitle.movie" class="row justify-content-start mt-2">
                        <div class="col-5 text-white">
                            <input type="text" class="form-control" placeholder="title of movie for search"
                                name="title">
                        </div>
                        <div class="col-1">
                            <button class="btn btn-dark text-center">Search</button>
                        </div>
                        <div class="col-1">
                            <a href="outputAll.movie"><button type=button class="btn btn-dark text-center">Revert</button></a>
                        </div>
                    </form>
                </div>
                <div class="list">
                    <div>
                        <table class="table">
                            <thead>
                                <tr>
                                    <th scope="col">ID</th>
                                    <th scope="col">Title</th>
                                    <th scope="col">Category</th>
                                    <th scope="col">Launch Time</th>
                                </tr>
                            </thead>
                        </table>
                    </div>
                    <div id="list">
                        <table class="table">
                            <tbody>
                                <c:choose>
                                    <c:when test="${not empty list}">
                                        <c:forEach var="i" items="${list}">
                                            <tr>
                                                <td>${i.id}</td>
                                                <td>${i.title}</td>
                                                <td>${i.genre}</td>
                                                <td>${i.launch_time}</td>
                                            </tr>
                                        </c:forEach>
                                    </c:when>
                                    <c:otherwise>
                                        출력할 내용이 없습니다.
                                    </c:otherwise>
                                </c:choose>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </body>

        </html>