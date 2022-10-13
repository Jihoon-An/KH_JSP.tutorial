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
                .form-control{
                    background-color: rgb(22, 22, 22);
                    color:white;
                }
            </style>
        </head>

        <body>
            <div class="container">
                <div class="header">
                    <div class="row">
                        <span class="col-12 text-center pt-5" id="title">Netflix</span>
                    </div>
                </div>
                <div class="main">
                    <div>
                        <form action="InsertController" class="row justify-content-center ">
                            <div class="col-4 text-end">
                                <div class="row">
                                    <input type="text" class="form-control" placeholder="title" name="title"><br>
                                    <input type="text" class="form-control" placeholder="category" name="genre"><br>
                                    <input type="date" class="form-control" placeholder="category" name="launch_date">
                                </div>
                            </div>
                            <div class="col-2 text-start position-relative">
                                <button class="btn btn-dark text-center position-absolute top-50 start-50 translate-middle">register<br>new movie</button>
                            </div>
                        </form>
                    </div>
                    <div class="row justify-content-center mt-5">
                        <div class="col-2 text-white">
                            <form action="OutputAllController">
                                <button class="btn btn-dark text-center">Output All</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </body>

        </html>