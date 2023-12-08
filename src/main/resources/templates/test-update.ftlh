<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h3>Редактирование теста</h3>
    <hr>
    <form action="/test/update/${test.id}" method="post" enctype="multipart/form-data">
        <div class="form-group">
            <label for="inputTitle">Название теста</label>
            <input type="text" class="form-control" id="inputTitle" name="title" value="${test.title}">
        </div>
        <div class="form-group">
            <label for="inputDescription">Описание теста</label>
            <input type="text" class="form-control" id="inputDescription" name="description" value="${test.description}">
        </div>
        <div class="form-group">
            <label for="inputPoints">Максимальное количество баллов за тест</label>
            <input type="number" class="form-control" id="inputPoints" name="points" value="${test.points}">
        </div>
        <input type="hidden" name="questionNumber" value="${test.questionNumber}">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit" class="btn btn-secondary btn-lg">Применить изменения</button>
    </form>
    <br>
    <a href="/test/edit-questions/${test.id}" style="text-decoration: none">
        <button type="button" class="btn btn-secondary btn-lg">Редактировать вопросы</button>
    </a>
    <br><br>
    <a href="/test" style="text-decoration: none">
        <button type="button" class="btn btn-secondary btn-lg">Выйти</button>
    </a>
</@t.template>