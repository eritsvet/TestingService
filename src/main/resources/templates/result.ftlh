<#import "blocks/template.ftlh" as t>
<@t.template user>
    <table class="table table-bordered">
        <thead>

        <tr>
            <th scope="col">Номер вопроса</th>
            <th scope="col">Ваш ответ</th>
            <th scope="col">Правильный ответ</th>
            <th scope="col">Полученный балл</th>
        </tr>
        </thead>
        <tbody>
        <#assign counter = 0>
        <#assign userScore = 0>
<#--        <#assign maxScore = 0>-->
        <#list answers as answer>
            <#assign counter = counter + 1>
            <tr>
                <td>${counter}</td>
                <td>${answer.answerNumber}</td>
                <td>${answer.question.answer}</td>
                <td>
<#--                    <#assign maxScore = maxScore + answer.question.points>-->
                    <#if answer.answerNumber == answer.question.answer>
                        ${answer.question.points}/${answer.question.points}
                        <#assign userScore = userScore + answer.question.points>
                    <#else>
                        0/${answer.question.points}
                    </#if>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>
    <h2>Итоговый балл: ${userScore}/${test.points} </h2>
    <form action="/result/save" method="post" enctype="multipart/form-data">
        <input type="hidden" name="testId" value="${test.id}"/>
        <input type="hidden" name="userId" value="${user.id}"/>
        <input type="hidden" name="userScore" value="${userScore}"/>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit" class="btn btn-secondary btn-lg">Сохранить результат</button>
    </form>
    <br><br>
</@t.template>