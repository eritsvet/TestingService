<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Результаты</h1>
    <hr>
    <table class="table table-bordered">
        <thead>

        <tr>
            <th scope="col">Название теста</th>
            <th scope="col">Баллы</th>
        </tr>
        </thead>
        <tbody>
        <#list results as result>
            <tr>
                <td>${result.test.title}</td>
                <td>${result.userScore}</td>
            </tr>
        </#list>
        </tbody>
    </table>
    <a href="/test" style="font-size: 50px">
        <button type="button" class="btn btn-secondary btn-lg">
            Перейти к тестированиям
        </button>
    </a>
</@t.template>