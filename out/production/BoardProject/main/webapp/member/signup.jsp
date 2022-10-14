<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>Sign up</title>
            <link rel="shortcut icon" type="image/x-icon" href="resource/duck.ico">
            <script src="https://code.jquery.com/jquery-3.6.1.min.js"
                integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous">
                </script>
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet"
                integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT"
                crossorigin="anonymous">
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8"
                crossorigin="anonymous"></script>
            <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
            <!-- 폰트 -->
            <link href="https://fonts.googleapis.com/css2?family=Dongle:wght@700&family=Yeon+Sung&display=swap"
                rel="stylesheet">
            <!-- font-family: 'Dongle', sans-serif;  font-family: 'Yeon Sung', cursive; -->

            <style>
                * {
                    box-sizing: border-box;
                }

                .input-group-text {
                    background-color: azure;
                    font-family: 'Yeon Sung', cursive;
                }

                .btn {
                    font-size: large;
                    font-family: 'Dongle', sans-serif;
                }
            </style>
        </head>

        <body>
            <div class="container-md">
                <div class="row justify-content-center">
                    <div class="col-6">

                        <div class="row p-3">
                            <div class="col-10 header h1 text-start">
                                <span>Sign Up</span>
                            </div>
                            <div class="col-2 text-end">
                                <a href="/index.jsp"><button type="button" class="btn-close m-3"
                                        aria-label="Close"></button></a>
                            </div>
                        </div>

                        <form action="signup.member" id="singupForm" method="post">
                            <script>
                                var idCheck = false;
                                let pwCheck = false;
                                let nameCheck = false;
                                let phoneCheck = false;
                            </script>

                            <div class="row">
                                <div class="col">
                                    <div class="input-group mb-3">
                                        <span id="idHead" class="input-group-text col-3">아이디</span>
                                        <input name="id" id="id" type="text" class="form-control"
                                            aria-label="Amount (to the nearest dollar)">
                                        <button class="btn btn-outline-secondary" type="button" id="dupleCheck"
                                            onclick="idConfirm()">중복확인</button>
                                    </div>
                                </div>
                            </div>
                            <script>
                                function idConfirm() {
                                    if ($("#id").val() != "") {
                                        if ($("#id").val().length >= 6 && $("#id").val().length <= 20) {
                                            console.log(idCheck);
                                            window.open("dupleCheck.member?id=" + $("#id").val(), "", "width=400, height=300")
                                            //idCheck = true;
                                        }
                                        // 페이지를 열면서 데이터 전송.
                                        else {
                                            idCheck = false;
                                            Swal.fire('아이디 길이 확인!')
                                        }
                                    }
                                    else {
                                        idCheck = false;
                                        Swal.fire('Null')
                                    }
                                }
                            </script>

                            <div class="row">
                                <div class="col">
                                    <div class="input-group mb-3">
                                        <span class="input-group-text col-3" id="pw1Head">비밀번호</span>
                                        <input name="pw1" id="pw1" type="password" class="form-control"
                                            placeholder="password" aria-label="Username"
                                            aria-describedby="basic-addon1">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col">
                                    <div class="input-group mb-3">
                                        <span class="input-group-text col-3" id="pw2Head">비밀번호:re</span>
                                        <input name="pw2" id="pw2" type="password" class="form-control"
                                            placeholder="password confirm" aria-label="Username"
                                            aria-describedby="basic-addon1">
                                    </div>
                                </div>
                            </div>

                            <script>
                                let pw1 = document.getElementById("pw1");
                                let pw2 = document.getElementById("pw2");

                                let pw1Head = document.getElementById("pw1Head");
                                let pw2Head = document.getElementById("pw2Head");

                                pw1.onkeyup = function () {
                                    if (!pw1.value) {
                                        pw1Head.style = "background-color: red;";
                                        pwCheck = false;
                                    }
                                    else if (pw2.value && (pw1.value == pw2.value)) {
                                        if (8 <= pw1.value.length && pw1.value.length <= 16) {
                                            pw1Head.style = "background-color: aqua;";
                                            pw2Head.style = "background-color: aqua;";
                                            pwCheck = true;
                                        }
                                        else {
                                            pw1Head.style = "background-color: red;";
                                            pw2Head.style = "background-color: red;";
                                            pwCheck = false;
                                        }
                                    }
                                    else if (pw2.value && (pw1.value != pw2.value)) {
                                        pw1Head.style = "background-color: red;";
                                        pw2Head.style = "background-color: red;";
                                        pwCheck = false;
                                    }
                                };
                                pw2.onkeyup = function () {
                                    if (!pw2.value || !pw1.value) {
                                        pw2Head.style = "background-color: red;";
                                        pwCheck = false;
                                    }
                                    else if (pw1.value == pw2.value) {
                                        if (8 <= pw1.value.length && pw1.value.length <= 16) {
                                            pw1Head.style = "background-color: aqua;";
                                            pw2Head.style = "background-color: aqua;";
                                            pwCheck = true;
                                        }
                                        else {
                                            pw1Head.style = "background-color: red;";
                                            pw2Head.style = "background-color: red;";
                                            pwCheck = false;
                                        }
                                    }
                                    else {
                                        pw1Head.style = "background-color: red;";
                                        pw2Head.style = "background-color: red;";
                                        pwCheck = false;
                                    }
                                };
                            </script>

                            <div class="row">
                                <div class="col">
                                    <div class="input-group mb-3">
                                        <span class="input-group-text col-3">이름</span>
                                        <input id="name" name="name" type="text" class="form-control"
                                            aria-label="Username" aria-describedby="basic-addon1">
                                    </div>
                                </div>
                            </div>
                            <script>
                                let name = document.getElementById("name");

                                name.onkeyup = function () {
                                    if ($("#name").val() != null) {
                                        nameCheck = true;
                                    } else {
                                        nameCheck = false;
                                    }
                                };
                            </script>


                            <div class="row">
                                <div class="col">
                                    <div class="input-group mb-3">
                                        <span class="input-group-text col-3" id="basic-addon1">전화번호</span>
                                        <input name="phone" type="text" class="form-control" aria-label="Username"
                                            aria-describedby="basic-addon1">
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col">
                                    <div class="input-group mb-3">
                                        <span class="input-group-text col-3" id="basic-addon1">이메일</span>
                                        <input name="email" type="text" class="form-control" aria-label="Username"
                                            aria-describedby="basic-addon1">
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col">
                                    <div class="input-group mb-3">
                                        <span class="input-group-text col-3">우편번호</span>
                                        <input name="zipcode" id="zipcode" type="text" class="form-control"
                                            aria-label="Amount (to the nearest dollar)" readonly>
                                        <button class="btn btn-outline-secondary" type="button" id="addressBtn"
                                            onclick="postcode()">찾기</button>
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col">
                                    <div class="input-group mb-3">
                                        <span class="input-group-text col-3">주소</span>
                                        <input name="address1" id="address1" type="text" class="form-control"
                                            aria-label="Username" aria-describedby="basic-addon1" readonly>
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col">
                                    <div class="input-group mb-3">
                                        <span class="input-group-text col-3">상세주소</span>
                                        <input name="address2" id="address2" type="text" class="form-control"
                                            aria-label="Username" aria-describedby="basic-addon1">
                                    </div>
                                </div>
                            </div>
                            <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
                            <script>
                                function postcode() {
                                    new daum.Postcode({
                                        oncomplete: function (data) {
                                            document.getElementById('zipcode').value = data.zonecode;
                                            document.getElementById("address1").value = data.roadAddress;
                                        },
                                        theme: {
                                            bgColor: "rgb(252, 252, 252)", //바탕 배경색
                                            searchBgColor: "rgb(252, 252, 252)", //검색창 배경색
                                            contentBgColor: "rgb(252, 252, 252)", //본문 배경색(검색결과,결과없음,첫화면,검색서제스트)
                                            pageBgColor: "rgb(252, 252, 252)", //페이지 배경색
                                            textColor: "#202020", //기본 글자색
                                            queryTextColor: "#202020", //검색창 글자색
                                            //postcodeTextColor: "", //우편번호 글자색
                                            //emphTextColor: "", //강조 글자색
                                            outlineColor: "#444444" //테두리
                                        }
                                    }).open();
                                }
                            </script>

                            <div class="row">
                                <div class="col-6 text-end"><button type="submit" class="btn btn-primary">회원가입</button>
                                </div>
                                <div class="col-6 text-start"><a href="signup.jsp" class="btn btn-primary">초기화</a>
                                </div>
                            </div>
                        </form>
                        <script>
                            let singupForm = document.getElementById("singupForm");

                            singupForm.onsubmit = function () {
                                if (!(idCheck && pwCheck && nameCheck)) {
                                    Swal.fire({
                                        icon: 'error',
                                        title: '!!!!!!!!!!!!!실패!!!!!!!!!!!!!',
                                        text: '다시 입력하세요!',
                                        footer: '<a href="">Why do I have this issue?</a>'
                                    })
                                    return false;
                                }
                            }
                        </script>


                    </div>
                </div>
            </div>
        </body>

        </html>