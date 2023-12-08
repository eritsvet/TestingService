<#import "blocks/template.ftlh" as t>
<@t.template user>
    <#list tests as test>
        <#assign flag = true>
        <#list answers as answer>
            <#if user.email?? && test.id == answer.test.id && user.id == answer.user.id>
                <div class="card text-white my-3 product" style="background-color: rgb(71, 72, 89)">
                    <div class="card-body">
                        <h5 class="card-title" style="color: white;">${test.title}</h5>
                        <#--                        <p class="card-text">${product.price} ₽</p>-->
                    </div>
                    <div class="card-footer">
                        <#--                        ${product.city}-->
                        <p class="card-text" style="color: white;">Тест пройден</p>
                        <#if user.id?? && user.isAdmin()>
                            <form action="/test/delete/${test.id}" method="post">
                                <input type="hidden" name="_csrf" value="${_csrf.token}">
                                <button type="submit" class="btn btn-secondary btn-sm">Удалить</button>
                            </form>
                            <br>
                            <a href="/test/edit/${test.id}" style="text-decoration: none">
                                <button type="button" class="btn btn-secondary btn-sm">Редактировать</button>
                            </a>
                        </#if>
                    </div>
                </div>
                <#assign flag = false>
                <#break>
            </#if>
        </#list>
        <#if flag>
            <a href="/test/${test.id}" class="product-link" style="text-decoration: none;">
                <div class="card text-white my-3 product" style="background-color: rgb(88, 90, 140)">
                    <div class="card-body">
                        <h5 class="card-title" style="color: white;">${test.title}</h5>
                    <#--                        <p class="card-text">${product.price} ₽</p>-->
                    </div>
                    <div class="card-footer">
                    <#--                        ${product.city}-->
                        <p class="card-text" style="color: white;">${test.description}</p>
                        <#if user.id?? && user.isAdmin()>
                            <form action="/test/delete/${test.id}" method="post">
                                <input type="hidden" name="_csrf" value="${_csrf.token}">
                                <button type="submit" class="btn btn-secondary btn-sm">Удалить</button>
                            </form>
                            <br>
                            <a href="/test/edit/${test.id}" style="text-decoration: none">
                                <button type="button" class="btn btn-secondary btn-sm">Редактировать</button>
                            </a>
                        </#if>
                    </div>
                </div>
            </a>
        </#if>
    <#else>
        <h4 style="text-align: center">Не найдено</h4>
    </#list>
</@t.template>