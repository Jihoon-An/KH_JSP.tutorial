<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>자유게시판</title>
            <link rel="shortcut icon" type="image/x-icon" href="/resource/duck.ico">
            <script src="https://code.jquery.com/jquery-3.6.1.min.js"
                integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous">
                </script>

            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet"
                integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT"
                crossorigin="anonymous">
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8"
                crossorigin="anonymous"></script>

            <!-- font -->
            <link href="https://fonts.googleapis.com/css2?family=Dongle:wght@700&family=Yeon+Sung&display=swap"
                rel="stylesheet">
            <style>
                .font-dongle {
                    line-height: 80%;
                    font-family: 'Dongle', sans-serif;
                    font-size: 130%;
                }

                .font-yeonsung {
                    font-family: 'Yeon Sung', cursive;
                }

                .content-date,
                .content-writer,
                .content-title,
                .content-seq {
                    font-family: 'Dongle', sans-serif;
                }

                #contentsDiv {
                    height: max-content;
                }

                #mainCont {
                    overflow: hidden;
                    border-radius: 20px;
                }

                .pageNum {
                    font-size: large;
                    margin: 10px;
                }

                .pageNum:hover {
                    cursor: pointer;
                }

                .contentA{
                    text-decoration: none;
                }
         
                .content {
                    font-family: 'Dongle', sans-serif;
                }
                .content:hover{
                    background-color: rgb(222, 235, 241);
                }
            </style>
        </head>

        <body>
            <div id="headCont" class="container p-3">
                <div id="header" class="text-center font-yeonsung">
                    <h1>자유게시판</h1>
                </div>
            </div>

            <div id="mainCont" class="container">

                <div id="colName" class="row bg-info">
                    <div class="col-xl-1 d-none d-xl-block"></div>
                    <div class="col-xl-6 col-lg-6 col-8 text-center font-yeonsung">
                        <h2>제목</h2>
                    </div>
                    <div class="col-xl-2 col-lg-3 col-2 text-center font-yeonsung">
                        <h2>작성자</h2>
                    </div>
                    <div class="col-xl-2 col-lg-3 d-none d-lg-block text-center font-yeonsung">
                        <h2>날짜</h2>
                    </div>
                    <div class="col-2 col-lg-1 text-center font-yeonsung">
                        <h2>조회</h2>
                    </div>
                </div>

                <div class="row bg-light" id="contentsDiv">
                    <div class="col" id="contents">
                        <c:forEach var="posts" items="${posts}">
                            <a class="contentA" href="/detail.board?postNum=${posts.postNum}">
                                <div class="row table table-hover content">
                                    <div class="col-xl-1 col-lg-1 d-none d-xl-block text-center content-seq">
                                        <h2>${posts.postNum}</h2>
                                    </div>
                                    <div class="col-xl-6 col-lg-6 col-8 text-center content-title">
                                        <h2>${posts.title}</h2>
                                    </div>
                                    <div class="col-xl-2 col-lg-3 col-2 text-center content-writer">
                                        <h2>${posts.writer}</h2>
                                    </div>
                                    <div class="col-xl-2 col-lg-3 d-none d-lg-block text-center content-date">
                                        <h2>${posts.writeDate}</h2>
                                    </div>
                                    <div class="col-2 col-lg-1 text-center content-date">
                                        <h2>${posts.viewCount}</h2>
                                    </div>
                                </div>
                            </a>
                        </c:forEach>
                    </div>
                </div>
            </div>

            <div id="footerCont" class="container">
                <div class="row">
                    <div class="col-2"><a href="/index.jsp"
                            class="font-dongle btn btn-light m-2 border border-info">메인</a></div>
                    <div class="col-8 text-center">
                        <span class="pageNum">1</span>
                        <span class="pageNum">2</span>
                        <span class="pageNum">3</span>
                        <span class="pageNum">4</span>
                        <span class="pageNum">5</span>
                        <span class="pageNum">6</span>
                        <span class="pageNum">7</span>
                        <span class="pageNum">8</span>
                    </div>
                    <div class="col-2 text-end">
                        <!-- <form action="/freePost.board"><button type="submit"
                                class="btn btn-light m-2 border border-info">작성하기</button></form> -->
                        <a href="/freePost.board" class="font-dongle btn btn-light m-2 border border-info">작성하기</a>
                    </div>
                </div>
            </div>
        </body>

        </html>