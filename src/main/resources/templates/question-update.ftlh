<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h3>Редактирование теста</h3>
    <hr>
    <form action="/test/update-questions" method="post" enctype="multipart/form-data">
        <input type="hidden" name="title" value="${test.title}">
        <#list questions as question>
            <input type="hidden" name="questionIds" value="${question.id}">
            <div class="product-link">
                <div class="card text-white my-3 product" style="background-color: rgb(29, 123, 163)">
                    <div class="card-body" style="background-color: rgb(41, 170, 226)">
                        <div class="form-group card-title">
                            <label for="inputDescription">Вопрос</label>
                            <input type="text" class="form-control" id="inputDescription" name="descriptions" value="${question.description}">
                        </div>
                    </div>
                    <div class="card-footer bg-transparent border-light">
                        <div class="form-group card-title">
                            <label for="inputPoints">Балл за правильный ответ</label>
                            <input type="number" class="form-control" id="inputPoints" name="points" value="${question.points}">
                        </div>
                        <#list variants as variant>
                            <div class="form-group card-title">
                                <#if variant.question.id == question.id && variant.id%4==1>
                                    <input type="hidden" name="variantsIds" value="${variant.id}">
                                    <label for="inputVar1">Первый вариант ответа</label>
                                    <input type="text" class="form-control" id="inputVar1" name="var1" value="${variant.title}">
                                </#if>
                            </div>
                            <div class="form-group card-title">
                                <#if variant.question.id == question.id && variant.id%4==2>
                                    <input type="hidden" name="variantsIds" value="${variant.id}">
                                    <label for="inputVar2">Второй вариант ответа</label>
                                    <input type="text" class="form-control" id="inputVar2" name="var2" value="${variant.title}">
                                </#if>
                            </div>
                            <div class="form-group card-title">
                                <#if variant.question.id == question.id && variant.id%4==3>
                                    <input type="hidden" name="variantsIds" value="${variant.id}">
                                    <label for="inputVar3">Третий вариант ответа</label>
                                    <input type="text" class="form-control" id="inputVar3" name="var3" value="${variant.title}">
                                </#if>
                            </div>
                            <div class="form-group card-title">
                                <#if variant.question.id == question.id && variant.id%4==0>
                                    <input type="hidden" name="variantsIds" value="${variant.id}">
                                    <label for="inputVar4">Четвертый вариант ответа</label>
                                    <input type="text" class="form-control" id="inputVar4" name="var4" value="${variant.title}">
                                </#if>
                            </div>
                        </#list>
                        <div class="form-group card-title">
                            <label for="inputAnswer">Номер верного ответа</label>
                            <input type="text" class="form-control" id="inputAnswer" name="answers" value="${question.answer}">
                        </div>
                    </div>
                </div>
            </div>
        </#list>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit" class="btn btn-secondary btn-lg">Применить изменения</button><br><br>
    </form>
</@t.template>