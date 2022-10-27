<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>ajax</title>
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
            <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        </head>

        <body>
            <div class="controler">

                <fieldset>
                    <legend>Exam01</legend>
                    <div class="row">
                        <div class="col-2">
                            <button id="exam01" class="form-control">Exam01</button>
                            <script>
                                $("#exam01").on("click", function () {
                                    $.ajax({
                                        url: "/exam01.ajax",
                                        type: "post"

                                    });
                                })
                            </script>
                        </div>
                    </div>
                </fieldset>

                <fieldset>
                    <legend>Exam02</legend>
                    <div class="row">
                        <div class="col-2">
                            <button id="exam02" class="form-control">Exam02</button>

                            <script>
                                $("#exam02").on("click", function () {
                                    $.ajax({
                                        url: "/exam02.ajax",
                                        type: "post",
                                        data: {
                                            key1: "value1",
                                            key2: "value2"
                                        }
                                    });
                                })
                            </script>
                        </div>
                    </div>
                </fieldset>

                <fieldset>
                    <legend>Exam03</legend>
                    <div class="row">
                        <div class="col-2">
                            <button id="exam03" class="form-control">Exam03</button>

                            <script>
                                $("#exam03").on("click", function () {
                                    $.ajax({
                                        url: "/exam03.ajax",
                                        type: "post"

                                    }).done(function (response) {
                                        console.log(response);
                                    });
                                })
                            </script>
                        </div>
                    </div>
                </fieldset>

                <fieldset>
                    <legend>Exam04</legend>
                    <div class="row">
                        <div class="col-2">
                            <button id="exam04" class="form-control">Exam04</button>

                            <script>
                                $("#exam04").on("click", function () {
                                    $.ajax({
                                        url: "/exam04.ajax",
                                        type: "post"
                                    }).done(function (response) {
                                        let array = JSON.parse(response);
                                        console.log(array[0] + " " + array[1] + " " + array[2] + " ");
                                    });
                                })
                            </script>
                        </div>
                    </div>
                </fieldset>

                <fieldset>
                    <legend>Exam05</legend>
                    <div class="row">
                        <div class="col-2">
                            <button id="exam05" class="form-control">Exam05</button>

                            <script>
                                $("#exam05").on("click", function () {
                                    $.ajax({
                                        url: "/exam05.ajax",
                                        type: "post"
                                    }).done(function (response) {
                                        let data = JSON.parse(response); // 역직렬화
                                        console.log(data.title);
                                    });
                                })
                            </script>
                        </div>
                    </div>
                </fieldset>
                
                <fieldset>
                    <legend>Exam06</legend>
                    <div class="row">
                        <div class="col-2">
                            <button id="exam06" class="form-control">Exam06</button>

                            <script>
                                $("#exam06").on("click", function () {
                                    $.ajax({
                                        url: "/exam06.ajax",
                                        type: "post",
                                        dataType:"json"
                                    }).done(function (response) {
                                        //let data = JSON.parse(response); // 역직렬화(dataType 설정을 해줘서 여기서 할 필요 없음.)
                                        console.log(data[0].title);
                                    });
                                })
                            </script>
                        </div>
                    </div>
                </fieldset>
                
                <fieldset>
                    <legend>Exam07</legend>
                    <div class="row">
                        <div class="col-2">
                            <button id="exam07" class="form-control">Exam07</button>

                            <script>
                                $("#exam07").on("click", function () {
                                    $.ajax({
                                        url: "/exam07.ajax",
                                        type: "post",
                                        dataType:"json"
                                    }).done(function (response) {
                                        //let data = JSON.parse(response); // 역직렬화(dataType 설정을 해줘서 여기서 할 필요 없음.)
                                        console.log(response);
                                        console.log(JSON.parse(response.obj));
                                        console.log(JSON.parse(response.dto));
                                    });
                                })
                            </script>
                        </div>
                    </div>
                </fieldset>
            </div>
        </body>

        </html>