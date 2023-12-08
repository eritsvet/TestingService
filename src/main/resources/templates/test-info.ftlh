<#import "blocks/template.ftlh" as t>
<@t.template user>
    <form action="/test/save-answers" method="post" enctype="multipart/form-data">
        <#list questions as question>
            <div class="product-link">
                <div class="card text-white my-3 product" style="background-color: rgb(75, 82, 11)">
                    <div class="card-body" style="background-color: rgb(105, 115, 16)">
                        <h5 class="card-title" style="font-size: 24px">${question.description}</h5>
                    </div>
                    <div class="card-footer bg-transparent border-light">
                        <#assign counter = 0>
                        <#list variants as variant>
                            <#if question.id == variant.question.id>
                                <#assign counter = counter + 1>
                                <p class="card-text">${counter}. ${variant.title}</p>
                            </#if>
                        </#list>
                        <input type="hidden" name="testIds" value="${question.test.id}"/>
                        <input type="hidden" name="questionIds" value="${question.id}"/>
                        <input type="hidden" name="userIds" value="${user.id}"/>
<#--                        Номер правильного ответа: <input type="number" name="answer"/><br>-->
                        <div style="font-size: 20px">
                            <label for="inputAnswer">Номер правильного ответа</label>
                        </div>
                        <div class="form-group" style="width: 250px">
                            <input type="number" class="form-control" id="inputAnswer" name="answer">
                        </div>
                    </div>
                </div>
            </div>
        <#else>
            <h4 style="text-align: center">Не найдено</h4>
        </#list>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
<#--        <input type="submit" value="Сохранить ответы"/>-->
        <button type="submit" class="btn btn-secondary btn-lg">Сохранить ответы</button> <br><br>
    </form>
</@t.template>