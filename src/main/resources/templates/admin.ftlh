<#import "blocks/template.ftlh" as t>
<@t.template user>
    `<h1 style="text-align: center">Панель администратора</h1>
    <hr>
    <#if user.email?? && user.isAdmin()>
        <h3 data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false"
            aria-controls="collapseExample">Создание нового теста</h3>
        <hr>
        <form action="/test/create" method="post" enctype="multipart/form-data" class="collapse"
              id="collapseExample">
            <div class="form-group">
                <label for="inputTitle">Название теста</label>
                <input type="text" class="form-control" id="inputTitle" name="title">
            </div>
            <div class="form-group">
                <label for="inputDescription">Описание теста</label>
                <input type="text" class="form-control" id="inputDescription" name="description">
            </div>
            <div class="form-group">
                <label for="inputPoints">Максимальное количество баллов за тест</label>
                <input type="number" class="form-control" id="inputPoints" name="points">
            </div>
            <div class="form-group">
                <label for="inputQuestionNumber"> Количество вопросов</label>
                <input type="number" class="form-control" id="inputQuestionNumber" name="questionNumber">
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button type="submit" class="btn btn-secondary btn-lg">Добавить тест</button>
        </form>
    </#if>
    <hr>
    <h3 data-toggle="collapse" href="#collapseExample1" role="button" aria-expanded="false"
        aria-controls="collapseExample">Информация о пользователях</h3>
    <hr>
    <table class="table table-bordered table-hover collapse" id="collapseExample1">
        <tr>
            <th>Email</th>
            <th>Номер телефона</th>
            <th>Активность</th>
            <th>Роли</th>
            <th>Бан</th>
            <th>Редактирование</th>
            <th>Результаты</th>
        </tr>
        <#list users as user>
            <tr>
                <th>${user.email}</th>
                <th>${user.phoneNumber}</th>
                <th>
                    <#if user.active>
                        true
                    <#else>
                        false
                    </#if>
                </th>
                <th><#list user.roles as role>${role}</#list></th>
                <th>
                    <form action="/admin/user/ban/${user.id}" method="post">
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                        <input type="submit" value="Бан">
                    </form>
                </th>
                <th><a href="/admin/user/edit/${user.id}">Редактирование</a></th>
                <th>
                    <#if user.email?? && user.isAdmin()>
                        —
                    <#else>
                    <a href="/my-results/${user.id}">Результаты</a>
                    </#if>
                </th>
            </tr>
        <#else>
            <h3>Пользователей нет</h3>
        </#list>
    </table>
    <br><br>
</@t.template>