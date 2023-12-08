<#import "blocks/template.ftlh" as t>
<@t.template user>
    <div class="mx-auto" style="width: 500px">
        <img src="/static/images/tests.png" alt="mdo" width="500">
    </div>
    <br>
    <div class="mx-auto" style="text-align: center">
        <h3>Сервис онлайн тестирования для студентов</h3>
        <hr>
        <a href="/test" style="font-size: 50px">
            <button type="button" class="btn btn-lg btn-outline-dark" style="width: 700px; height: 100px; font-size: 50px">
                Перейти к тестированиям
            </button>
        </a>
    </div>
<#--    <a href="/test" style="font-size: 50px">ТЫКТЫКТЫТКТЫКТЫКТЫК</a>-->
<#--    <div class="card-columns">-->
<#--        <#list products as product>-->
<#--            <a href="/product/${product.id}" class="product-link">-->
<#--                <div class="card text-white my-3 product" style="background-color: rgb(86,77,66)">-->
<#--                    <img src="/images/${product.previewImageId}"-->
<#--                         class="card-img-top">-->
<#--                    <div class="card-body">-->
<#--                        <h5 class="card-title">${product.title}</h5>-->
<#--                        &lt;#&ndash;                        <p class="card-text">${product.price} ₽</p>&ndash;&gt;-->
<#--                    </div>-->
<#--                    <div class="card-footer">-->
<#--                        &lt;#&ndash;                        ${product.city}&ndash;&gt;-->
<#--                        <p class="card-text">${product.price} ₽</p>-->
<#--                    </div>-->
<#--                </div>-->
<#--            </a>-->
<#--        <#else>-->
<#--            <h4 style="text-align: center">Не найдено</h4>-->
<#--        </#list>-->
<#--    </div>-->
</@t.template>