<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>중복체크</title>
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
            <style>
                table {
                    width: 100%;
                    height: 90vh;
                }

                .result {
                    height: 70%;
                    text-align: center;

                }
            </style>
        </head>

        <body>
            <table>
                <c:choose>
                    <c:when test="${result}">
                        <tr>
                            <td class="result">"${id}" 사용 불가</td>
                        </tr>
                        <tr>
                            <td><button id="close">닫기</button></td>
                            <script>
                                document.getElementById("close").onclick = function () {
                                    opener.document.getElementById("id").value = "";
                                    opener.document.getElementById("idHead").style = "background-color:red;";
                                    opener.idCheck = false;
                                    window.close();
                                }
                            </script>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td class="result">"${id}" 사용 가능</td>
                        </tr>
                        <tr>
                            <td>
                                <button id="use">사용</button>
                                <button id="cancel">취소</button>
                            </td>
                            <script>
                                document.getElementById("use").onclick = function () {
                                    opener.document.getElementById("idHead").style = "background-color:aqua;";
                                    opener.idCheck = true;
                                    window.close();
                                }
                                document.getElementById("cancel").onclick = function () {
                                    opener.document.getElementById("id").value = "";
                                    window.close();
                                }
                            </script>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </table>
        </body>

        </html>