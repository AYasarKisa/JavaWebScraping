
const printAlert = function(text, display){
    let alert = document.querySelector('#alert');
    alert.style.display = display;
    alert.innerHTML = text;
}

const question1 = async function(){
    let url = document.querySelector('#input-question1').value;
    let fullUrl = "http://localhost:8080/api1/soru1/frekansbul?url="+url;
    await $.ajax({
            type: "GET",
            url: fullUrl,
            dataType: "json",
            success: function (response) {
                console.log(response)
                printAlert("",'none')
            },
            error: function(){
                printAlert("bir hata oluştu",'block')
            }
        });
}

const question2 = async function(){
    let url = document.querySelector('#input-question2').value;
    let fullUrl = "http://localhost:8080/api1/soru2/keywords?url="+url;
    await $.ajax({
            type: "GET",
            url: fullUrl,
            dataType: "json",
            success: function (response) {
                console.log(response)
                printAlert("",'none')
            },
            error: function(){
                printAlert("bir hata oluştu",'block')
            }
        });
}

const question3 = async function(){
    let url1 = document.querySelector('#input-question3-1').value;
    let url2 = document.querySelector('#input-question3-2').value;
    let fullUrl = "http://localhost:8080/api1/soru3/similarity?url1="+url1+"&url2="+url2;
    await $.ajax({
            type: "GET",
            url: fullUrl,
            dataType: "json",
            success: function (response) {
                console.log(response)
                printAlert("",'none')
            },
            error: function(){
                printAlert("bir hata oluştu",'block')
            }
        });
}

document.body.addEventListener('click', function(e){
    if(e.target.id === 'btn-question1'){
        printAlert("yükleniyor",'block')
        question1()
    }else if(e.target.id === 'btn-question2'){
        printAlert("yükleniyor",'block')
        question2()
    }else if(e.target.id === 'btn-question3'){
        printAlert("yükleniyor",'block')
        question3()
    }else if(e.target.id === 'btn-question4'){
        
    }else if(e.target.id === 'btn-question5'){
        
    }
})