
const printAlert = function(text, display){
    let alert = document.querySelector('#alert');
    alert.style.display = display;
    alert.innerHTML = text;
}

const question1Result = async function(response){
    let textArea = document.querySelector('#text-area')
    textArea.style.display = 'block'
    let html=""
    for(let i=0;i<response.length;i++)
    {
        html+=(i+1)+"-  kelime:"+response[i].kelime+"  /  frekans:"+response[i].freakans+"\n"
    }
    textArea.innerText = html
}

const question2Result = async function(response){
    let textArea = document.querySelector('#text-area')
    textArea.style.display = 'block'
    let html=""

    for(let i=0;i<7;i++)(
        html+= (i+1)+"-  Anahtar Kelime: "+response[i].word+"    /    Anahtar Kelime Puanı: "+response[i].keywords+"\n"
    )
    textArea.innerText = html
}

const question3Result = async function(response){
    let textArea = document.querySelector('#text-area')
    textArea.style.display = 'block'
    let html = ""

    html += "url1: "+response[0].url + "\n"
    html += "url2: "+response[1].url + "\n"
    html += "benzerlik: "+response[0].similarity + "\n\n\n\n"
    console.log(html)
    html += "url 1 anahtar kelimeleri ve frekansları\n\n"
    for(let i=0;i<7;i++)(
        html += (i+1)+"-    anahtar kelime: "+response[0].keywords[i]+"   /    frekans: "+response[0].frequency[i]+"\n"
    )
    html += "\n\n\n"
    html += "url 2 anahtar kelimeleri ve frekansları\n\n"
    for(let i=0;i<7;i++)(
            html += (i+1)+"-    anahtar kelime: "+response[1].keywords[i]+"   /    frekans: "+response[1].frequency[i]+"\n"
    )
    textArea.innerText = html
}


const question4Result = async function(response){

    let html = ""
    if(response.length == 0){
       return 1
    }

    for(let i=0;i<response.length;i++){
        html += "url: "+response[i].url+"\n"
        html += "level: "+response[i].level+"\n"
        html += "total score: "+response[i].totalScore+"\n"
        html += "Anahtar Kelimeler ve Frekansları\n\n"
        for(let j=0;j<7;j++){
            html += j+"-  Anahtar Kelime: "+response[i].keywordFrequency[j].keyword+"   Frekans: "+response[i].keywordFrequency[j].frequency+"\n"
        }
        html +="\n\n\n"
        question4Result(response[i].subUrl)
    }
   textArea.innerText=result
}

const question1 = async function(){
    let url = document.querySelector('#input-question1').value;
    let fullUrl = "http://localhost:8080/api1/soru1/frekansbul?url="+url;
    await $.ajax({
            type: "GET",
            url: fullUrl,
            dataType: "json",
            success: function (response) {
                question1Result(response)
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
                question2Result(response)
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
                question3Result(response)
                printAlert("",'none')
            },
            error: function(){
                printAlert("bir hata oluştu",'block')
            }
        });
}

const question4 = async function(){
    let url1 = document.querySelector('#input-question4').value;
    let url2 = document.querySelector('#area-question4').value;
    let fullUrl = "http://localhost:8080/api1/soru4/indexandsort?mainUrl="+url1+"&urlSet="+url2;
    await $.ajax({
            type: "GET",
            url: fullUrl,
            dataType: "json",
            success: function (response) {
                console.log(response)
                question4Result(response)
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
        question4()
        printAlert("yükleniyor",'block')
    }else if(e.target.id === 'btn-question5'){
        
    }
})