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
                    height: 640px;
                    margin: auto;
                    margin-top: 50px;
                    border: 3px solid rgb(56, 150, 244);
                    border-radius: 10px;
                }

                #postContent {
                    resize: none;
                    outline: none;
                    overflow-y: scroll;
                    width: 100%;
                    height: 300px;
                    box-sizing: border-box;
                    border: 2px solid rgb(220, 255, 168);
                    border-radius: 3px;
                    background-color: rgb(244, 255, 239);
                }

                #postContent::-webkit-scrollbar {
                    display: none;
                }

                #postContent:focus {
                    outline: none;
                }

                #postContent:hover {
                    cursor: default;
                }

                a {
                    text-decoration: none;
                }

                #deleteBtn {
                    background-color: rgb(225, 35, 35);
                    color: white;
                    border-color: rgb(225, 35, 35);
                }

                #commentwrite {
                    height: 80px;
                    padding: 5px;
                    margin: 0;

                    resize: none;
                    outline: none;
                    overflow-y: scroll;
                    width: 100%;
                    box-sizing: border-box;
                    border: 2px solid rgb(220, 255, 168);
                    border-radius: 3px;
                    background-color: rgb(244, 255, 239);
                }

                #commentwrite::-webkit-scrollbar {
                    display: none;
                }

                #commentwrite:hover {
                    cursor: text;
                }

                #commentArea {
                    border: 2px solid rgb(56, 150, 244);
                    height: max-content;
                    margin-top: 3px;
                }

                .commentText {
                    margin-bottom: 5px;
                }

                .commentBtn {
                    background-color: none;
                    border: none;
                    border-radius: 5px;
                    margin: 3px;
                }
            </style>
        </head>

        <body>
            <div class="container p-3">
                <div class="row header">
                    <div class="row title font-yeonsung">
                        <h1>${post.title}</h1>
                    </div>
                    <div class="sub"><span class="fs-1 font-dongle p-0 m-0">${post.writer}</span>
                        <br>
                        <span class="fs-6 font-dongle">${post.writeDate}</span>
                        <span class="fs-6 mx-3 mt-0 font-dongle">조회수 ${post.viewCount}</span>
                    </div>
                </div>
                <div class="row main" id="content">
                    <textarea id="postContent" readonly>${post.content}</textarea>
                </div>
                <div class="footer text-end">
                    <button type="button" id="backBtn" class="font-dongle btn btn-outline-primary">뒤로가기</button>
                    <script>
                        $("#backBtn").on("click", function () { window.history.back(); });
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
                <div class="comments row pt-2">
                    <form method="post" action="/insert.comment" id="writeForm" class="p-0 m-0">
                        <div class="row mx-1">
                            <div class="col-5">
                                <h6 class="font-yeonsung" style="transform:translate(0,10px);">댓글 달기</h6>
                            </div>
                            <div class="col-2"></div>
                            <c:if test="${loginId != null}">
                                <div class="col-5 text-end p-0 m-0"><button type="button" id="writeBtn"
                                        class="m-0 font-dongle btn btn-light"
                                        style="height:35px; line-height:10px;">작성하기</button>
                                </div>
                            </c:if>
                        </div>
                        <div class="row m-0 p-0 pt-1">
                            <div id="commentwrite" contenteditable="true"></div>
                            <input type="hidden" id="contentsInput" name="contents" value="">
                            <input type="hidden" name="postNum" value="${post.postNum}">
                        </div>
                    </form>
                    <script>
                        $("#writeBtn").click(function () {
                            $("#contentsInput").val($("#commentwrite").html());
                            $("#writeForm").submit();
                        });
                    </script>
                </div>
            </div>

            <c:if test="${comments != null}">
                <c:forEach var="comment" items="${comments}">
                    <form action="/modify.comment" method="post" class="modifyForm">
                        <div class="container" id="commentArea">
                            <div class="row">
                                <div class="col-6">
                                    <span class="font-yeonsung">${comment.writerName}</span>
                                    <span class="font-dongle px-2 fs-6">${comment.writeTime}</span>
                                </div>

                                <c:if test="${loginId == comment.writerId}">

                                    <div class="col-6 text-end">
                                        <button type="button" class="commentBtn modifyBtn" id="modifyComment"
                                            style="display:inline;">수정</button>
                                        <button type="button" class="commentBtn completeBtn" id="completeBtn"
                                            style="display: none;">완료</button>
                                        <a
                                            href="/delete.comment?commentNum=${comment.commentNum}&postNum=${post.postNum}">
                                            <button type="button" class="commentBtn" id="deleteComment">삭제</button>
                                        </a>
                                    </div>
                                </c:if>
                                <input type="hidden" name="writerId" value="${comment.writerId}">
                                <input type="hidden" name="commentNum" value="${comment.commentNum}">
                                <input type="hidden" name="postNum" value="${post.postNum}">

                            </div>
                            <div class="row px-1">
                                <div id="commentContents" class="commentText" readonly>${comment.contents}</div>
                                <input class="commentInput" type="hidden" name="contents" value="">
                            </div>
                        </div>
                    </form>
                </c:forEach>
            </c:if>
            <script>
                var modifyBtns = $(".modifyBtn");
                var completeBtns = document.getElementsByClassName("completeBtn");
                var divs = document.getElementsByClassName("commentText");
                var inputs = document.getElementsByClassName("commentInput");
                var forms = document.getElementsByClassName("modifyForm");
                var length = forms.length;

                $(".modifyBtn").click(function () {
                    $(this).css("display", "none");
                    $(this).parent().children(".completeBtn").css("display", "inline");
                    $(this).closest("form").find(".commentText").attr("contenteditable", "true").focus();
                });
                $(".completeBtn").click(function () {
                    $(this).closest("form").find(".commentInput").val($(this).closest("form").find(".commentText").html());
                    $(this).closest("form").submit();
                });
            </script>
        </body>

        </html>