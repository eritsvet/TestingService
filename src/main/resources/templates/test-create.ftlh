<#import "blocks/template.ftlh" as t>
<@t.template user>
    `<h1 style="text-align: center">Создание теста</h1>
    <hr>
    <#if user.email?? && user.isAdmin()>
        <form action="/test/create-questions" method="post" enctype="multipart/form-data">
            <input type="hidden" name="title" value="${test.title}">
            <#list 1..test.questionNumber as number>
                <div class="product-link">
                    <div class="card text-white my-3 product" style="background-color: rgb(29, 123, 163)">
                        <div class="card-body" style="background-color: rgb(41, 170, 226)">
                            <div class="form-group card-title">
                                <label for="inputDescription">Вопрос</label>
                                <input type="text" class="form-control" id="inputDescription" name="descriptions">
                            </div>
                        </div>
                        <div class="card-footer bg-transparent border-light">
<#--                            Балл за правильный ответ: <input type="number" name="points"/><br><br>-->
<#--                            Первый вариант ответа: <input type="text" name="var1"/><br><br>-->
<#--                            Второй вариант ответа: <input type="text" name="var2"/><br><br>-->
<#--                            Третий вариант ответа: <input type="text" name="var3"/><br><br>-->
<#--                            Четвертый вариант ответа: <input type="text" name="var4"/><br><br>-->
<#--                            Номер верного ответа: <input type="text" name="answers"/><br><br>-->
                            <div class="form-group card-title">
                                <label for="inputPoints">Балл за правильный ответ</label>
                                <input type="number" class="form-control" id="inputPoints" name="points">
                            </div>
                            <div class="form-group card-title">
                                <label for="inputVar1">Первый вариант ответа</label>
                                <input type="text" class="form-control" id="inputVar1" name="var1">
                            </div>
                            <div class="form-group card-title">
                                <label for="inputVar2">Второй вариант ответа</label>
                                <input type="text" class="form-control" id="inputVar2" name="var2">
                            </div>
                            <div class="form-group card-title">
                                <label for="inputVar3">Третий вариант ответа</label>
                                <input type="text" class="form-control" id="inputVar3" name="var3">
                            </div>
                            <div class="form-group card-title">
                                <label for="inputVar4">Четвертый вариант ответа</label>
                                <input type="text" class="form-control" id="inputVar4" name="var4">
                            </div>
                            <div class="form-group card-title">
                                <label for="inputAnswer">Номер верного ответа</label>
                                <input type="text" class="form-control" id="inputAnswer" name="answers">
                            </div>
                        </div>
                    </div>
                </div>
            </#list>
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button type="submit" class="btn btn-secondary btn-lg">Добавить вопросы</button><br><br>
        </form>
    </#if>
</@t.template>