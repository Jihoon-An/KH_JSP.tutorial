<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>상세페이지</title>
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

                .container {
                    width: 800px;
                    height: 600px;
                    margin: auto;
                    margin-top: 50px;
                    border: 3px solid rgb(56, 150, 244);
                    border-radius: 10px;
                }

                textarea {
                    resize: none;
                    outline: none;
                    overflow-y: scroll;
                    width: 100%;
                    height: 380px;
                    box-sizing: border-box;
                    border: 2px solid rgb(220, 255, 168);
                    border-radius: 3px;
                    background-color: rgb(244, 255, 239);
                }
                textarea::-webkit-scrollbar {
                    display: none;
                }

                textarea:focus {
                    outline: none;
                }

                textarea:hover {
                    cursor: default;
                }

                a{
                    text-decoration: none;
                }

                #deleteBtn{
                    background-color: rgb(225, 35, 35);
                    color: white;
                    border-color: rgb(225, 35, 35);
                }

            </style>
        </head>

        <body>
            <div class="container p-3">
                <div class="row header">
                    <div class="row title font-yeonsung"><h1>${post.title}</h1></div>
                    <div class="sub"><span class="fs-1 font-dongle p-0 m-0">${post.writer}</span>
                        <br>
                        <span class="fs-6 font-dongle">조회수 ${post.viewCount}</span>
                        <span class="fs-6 mx-3 mt-0 font-dongle">${post.writeDate}</span>
                    </div>
                </div>
                <div class="row main" id="content">
                    <textarea name="" id="" cols="30" rows="10" readonly>${post.content}</textarea>
                </div>
                <script>
                    console.log("${post.content}");
                </script>
                <div class="footer text-end">
                    <button type="button" id="backBtn" class="font-dongle btn btn-outline-primary">뒤로가기</button>
                    <script>
                        $("backBtn").click(function(){
                            history.back;
                        });
                    </script>
                    <c:choose>
                        <c:when test="${loginId == post.id}">
                            <a href="/delete.board?postNum=${post.postNum}"><button id="deleteBtn"
                                    class="font-dongle btn btn-outline-primary">삭제하기</button></a>
                            <a href="/toModify.board?postNum=${post.postNum}"><button id="modifyBtn"
                                    class="font-dongle btn btn-outline-primary">수정하기</button></a>
                        </c:when>
                    </c:choose>
                    <a href="/freeBoard.board?cpage=1">
                        <button id="toMypageBtn" class="font-dongle btn btn-outline-primary">목록보기</button>
                    </a>
                    <a href="/">
                        <button id="mypage" class="font-dongle btn btn-outline-primary">메인으로</button>
                    </a>
                </div>
            </div>
        </body>

        </html>