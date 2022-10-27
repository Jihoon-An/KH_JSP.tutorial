<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>자유글 작성하기</title>
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
                    font-family: 'Dongle', sans-serif;
                    font-size: 130%;
                }

                .font-yeonsung {
                    font-family: 'Yeon Sung', cursive;
                }

                #content {
                    height: 400px;
                    resize: none;
                }

                .input {
                    border: 1px solid rgb(192, 192, 192);
                    border-radius: 5px;
                    outline-color: rgb(46, 99, 234);
                    outline-width: 2px;
                    margin-bottom: 10px;
                }
            </style>
        </head>

        <body>
            <c:choose>
                <c:when test="${loginId == null}">
                    <h1 class="font-dongle">로그인이 필요합니다.</h1>
                    <a href="/index.jsp">메인 화면으로 가기</a>
                </c:when>
                <c:otherwise>
                    <div id="headCont" class="container p-3">
                        <div class="row text-center">
                            <h1 class="font-yeonsung">글 작성</h1>
                        </div>
                    </div>
                    <form method="post" action="/postInFreeBoard.board" enctype="multipart/form-data">
                        <div id="postCont" class="container">
                            <div class="row justify-content-center">
                                <div class="col-6">
                                    <div class="row">
                                        <span class="col-2 border border-info bg-light text-center">제목</span>
                                        <input type="text" class="input" id="title" name="title"
                                            aria-describedby="basic-addon3">
                                    </div>
                                    <div class="mb-2 row">
                                        <div class="col-2">
                                            <button id="fileAdd" type="button"
                                                class="btn btn-outline-info mb-1">+</button>
                                        </div>
                                        <div class="col-10" id="fileInput"></div>
                                    </div>
                                    <script>
                                        let count = 0;
                                        $("#fileAdd").on("click", function () {
                                            if ($("input[type=file]").length > 4) {
                                                alert("배불러!");
                                                return;
                                            }
                                            
                                            let fileDiv = $("<div>");
                                            fileDiv.addClass("input-group mb-1");

                                            let inputFile = $("<input>");
                                            inputFile.attr("type", "file");
                                            inputFile.attr("name", "file" + count++);
                                            inputFile.addClass("form-control m-0");

                                            let delBtn = $("<button>");
                                            delBtn.html("X");
                                            delBtn.attr("type", "button");
                                            delBtn.addClass("border btn btn-light line-del");
                                            delBtn.on("click", function () { $(this).parent().remove(); });

                                            fileDiv.append(inputFile);
                                            fileDiv.append(delBtn);
                                            $("#fileInput").append(fileDiv);
                                        });
                                    </script>
                                    <div class="row">
                                        <span class="col-2 border border-info bg-light text-center">내용</span>
                                        <textarea type="text" class="input" id="content" name="content"
                                            aria-describedby="basic-addon3"></textarea>
                                    </div>
                                </div>
                            </div>

                            <div class="row justify-content-center">
                                <div class="col-3 text-start"><a href="/freeBoard.board?cpage=1" type="button"
                                        class="btn btn-primary font-dongle">게시판 목록</a></div>
                                <div class="col-3 text-end"><button type="submit"
                                        class="font-dongle btn btn-primary">작성하기</button>
                                </div>
                            </div>
                        </div>
                    </form>
                </c:otherwise>
            </c:choose>
        </body>

        </html>