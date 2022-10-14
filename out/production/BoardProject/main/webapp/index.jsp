<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>Main</title>
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

            <style>
                * {
                    box-sizing: border-box;
                }

                .loginBox {
                    border: 1px solid black;
                    position: absolute;
                    width: 500px;
                    height: 300px;
                    left: 50%;
                    top: 40px;
                    transform: translate(-50%);
                }
            </style>
        </head>

        <body>
            <div class="loginBox">
                <div class="container">
                    <div id="loginHeader" class="row text-center">
                        <span class="h1 fw-bold">Login Box</span>
                    </div>
                    <form action="" class="justify-content-center">
                        <div id="loginID" class="row input-group m-1 justify-content-center">
                            <span class="input-group-text col-3" id="basic-addon3">ID</span>
                            <input type="text" class="form-control col-9" id="basic-url" name="id"
                                aria-describedby="basic-addon3">
                        </div>
                        <div id="loginPW" class="row input-group m-1 justify-content-center">
                            <span class="input-group-text col-3" id="basic-addon3">Password</span>
                            <input type="password" class="form-control col-9" id="basic-url" name="pw"
                                aria-describedby="basic-addon3">
                        </div>
                        <div class="row justify-content-center pt-2">
                            <button id="loginBtn" class="col-3 btn btn-primary mx-2" type="submit">Login</button>
                            <a class="col-3 btn btn-primary mx-2" href="member/signup.jsp" role="button">Sign Up</a>
                        </div>
                        <div class="text-center mt-3">
                            <input class="form-check-input" type="radio" value="" name="checkRemeberId"
                                aria-label="Radio button for following text input">
                            <span>Remember ID</span>
                        </div>
                    </form>

                </div>
            </div>
        </body>

        </html>