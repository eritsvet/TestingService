<#macro template user>
    <!doctype html>
    <html lang="ru">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<#--        <link rel="stylesheet" href="/static/css/style.css">-->

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
              integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn"
              crossorigin="anonymous">

        <link rel="shortcut icon" href="/static/images/logo1.png" type="image/png">

        <title>Сервис онлайн тестирования</title>
    </head>
    <body style="background-color: rgb(204, 204, 204)">
    <nav class="navbar navbar-dark sticky-top border-bottom border-white" style="background-color: rgb(19, 80, 89)">
        <div class="container-fluid">
            <a href="/" style="text-decoration: none;">
                <img src="/static/images/logo1.png" alt="mdo"
                     width="32" height="32">
                <span class="navbar-brand mb-0 h1">Testing Service</span>
            </a>
            <#if user.email??>
                <#if user.isAdmin()>
                    <a href="/admin" class="ml-auto mr-3 text-white my-auto h5">Панель администратора</a>
                <#else>
                    <a href="/my-results/${user.id}" class="ml-auto mr-3">
                        <span class="navbar-brand mb-0 h1">Результаты</span>
                    </a>
                </#if>
                <div class="dropdown text-end my-auto">
                    <p class="text-white my-auto h5">user: ${user.name}</p>
                </div>
                <div class="dropdown text-end my-auto" style="margin-left: 15px">
                    <form action="/logout" method="post">
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                        <input type="submit" value="Выйти"/>
                    </form>
                </div>
            <#else>
                <button class="btn btn-light my-2 my-sm-0" type="button"
                        onclick="window.location.href = '/login'">
                    Войти
                </button>
            </#if>
        </div>
    </nav>
    <div class="container mt-4">
        <#nested/>
    </div>


    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
            integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"
            integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T"
            crossorigin="anonymous"></script>
    </body>
    </html>
</#macro>