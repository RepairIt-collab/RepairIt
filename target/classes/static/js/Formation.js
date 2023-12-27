document.getElementById("D1").addEventListener('click',function(){
    document.getElementById('formation').style.display="none";
    document.getElementById('Div1').style.display="block";
});

document.getElementById("D2").addEventListener('click',function(){
    document.getElementById('formation').style.display="none";
    document.getElementById('Div2').style.display="block";
});

document.getElementById("D3").addEventListener('click',function(){
    document.getElementById('formation').style.display="none";
    document.getElementById('Div3').style.display="block";
});

document.getElementById("D4").addEventListener('click',function(){
    document.getElementById('formation').style.display="none";
    document.getElementById('Div4').style.display="block";
});

document.getElementById("D5").addEventListener('click',function(){
    document.getElementById('formation').style.display="none";
    document.getElementById('Div5').style.display="block";
});

document.getElementById("D6").addEventListener('click',function(){
    document.getElementById('formation').style.display="none";
    document.getElementById('Div6').style.display="block";
});

document.getElementById("D7").addEventListener('click',function(){
    document.getElementById('formation').style.display="none";
    document.getElementById('Div7').style.display="block";
});

document.getElementById("D8").addEventListener('click',function(){
    document.getElementById('formation').style.display="none";
    document.getElementById('Div9').style.display="block";
});

// MASQUAGE SOUS MENU ELECTRICITE
document.getElementById("ButtonSelec").addEventListener('click',function(){
    document.getElementById('mems').style.display="block";
    document.getElementById('Div1').querySelector(".col-md-12").classList.replace('col-md-12', 'col-md-10');
    document.getElementById('ButtonSelec').style.display="none";
});

document.getElementById("retour").addEventListener('click',function(){
    document.getElementById('mems').style.display="none";
    document.getElementById('Div1').querySelector(".col-md-10").classList.replace('col-md-10', 'col-md-12');
    document.getElementById('ButtonSelec').style.display="block";
});

// MASQUAGE SOUS MENU MECANIQUE
document.getElementById("ButtonSelec2").addEventListener('click',function(){
    document.getElementById('mems2').style.display="block";
    document.getElementById('Div2').querySelector(".col-md-12").classList.replace('col-md-12', 'col-md-10');
    document.getElementById('ButtonSelec2').style.display="none";
});

document.getElementById("retour2").addEventListener('click',function(){
    document.getElementById('mems2').style.display="none";
    document.getElementById('Div2').querySelector(".col-md-10").classList.replace('col-md-10', 'col-md-12');
    document.getElementById('ButtonSelec2').style.display="block";
});

// MASQUAGE SOUS MENU TELEPHONE
document.getElementById("ButtonSelec3").addEventListener('click',function(){
    document.getElementById('mems3').style.display="block";
    document.getElementById('Div3').querySelector(".col-md-12").classList.replace('col-md-12', 'col-md-10');
    document.getElementById('ButtonSelec3').style.display="none";
});

document.getElementById("retour3").addEventListener('click',function(){
    document.getElementById('mems3').style.display="none";
    document.getElementById('Div3').querySelector(".col-md-10").classList.replace('col-md-10', 'col-md-12');
    document.getElementById('ButtonSelec3').style.display="block";
});

// MASQUAGE SOUS MENU ORDINATEUR
document.getElementById("ButtonSelec4").addEventListener('click',function(){
    document.getElementById('mems4').style.display="block";
    document.getElementById('Div4').querySelector(".col-md-12").classList.replace('col-md-12', 'col-md-10');
    document.getElementById('ButtonSelec4').style.display="none";
});

document.getElementById("retour4").addEventListener('click',function(){
    document.getElementById('mems4').style.display="none";
    document.getElementById('Div4').querySelector(".col-md-10").classList.replace('col-md-10', 'col-md-12');
    document.getElementById('ButtonSelec4').style.display="block";
});

// MASQUAGE SOUS MENU ELECTROMENAGER
document.getElementById("ButtonSelec5").addEventListener('click',function(){
    document.getElementById('mems5').style.display="block";
    document.getElementById('Div5').querySelector(".col-md-12").classList.replace('col-md-12', 'col-md-10');
    document.getElementById('ButtonSelec5').style.display="none";
});

document.getElementById("retour5").addEventListener('click',function(){
    document.getElementById('mems5').style.display="none";
    document.getElementById('Div5').querySelector(".col-md-10").classList.replace('col-md-10', 'col-md-12');
    document.getElementById('ButtonSelec5').style.display="block";
});

// MASQUAGE SOUS MENU PLOMBERIE
document.getElementById("ButtonSelec6").addEventListener('click',function(){
    document.getElementById('mems6').style.display="block";
    document.getElementById('Div6').querySelector(".col-md-12").classList.replace('col-md-12', 'col-md-10');
    document.getElementById('ButtonSelec6').style.display="none";
});

document.getElementById("retour6").addEventListener('click',function(){
    document.getElementById('mems6').style.display="none";
    document.getElementById('Div6').querySelector(".col-md-10").classList.replace('col-md-10', 'col-md-12');
    document.getElementById('ButtonSelec6').style.display="block";
});

// MASQUAGE SOUS MENU MENUISERIE
document.getElementById("ButtonSelec7").addEventListener('click',function(){
    document.getElementById('mems7').style.display="block";
    document.getElementById('Div7').querySelector(".col-md-12").classList.replace('col-md-12', 'col-md-10');
    document.getElementById('ButtonSelec7').style.display="none";
});

document.getElementById("retour7").addEventListener('click',function(){
    document.getElementById('mems7').style.display="none";
    document.getElementById('Div7').querySelector(".col-md-10").classList.replace('col-md-10', 'col-md-12');
    document.getElementById('ButtonSelec7').style.display="block";
});

// MASQUAGE SOUS MENU FROID ET CLIMATISATION
document.getElementById("ButtonSelec8").addEventListener('click',function(){
    document.getElementById('mems8').style.display="block";
    document.getElementById('Div8').querySelector(".col-md-12").classList.replace('col-md-12', 'col-md-10');
    document.getElementById('ButtonSelec8').style.display="none";
});

document.getElementById("retour8").addEventListener('click',function(){
    document.getElementById('mems8').style.display="none";
    document.getElementById('Div8').querySelector(".col-md-10").classList.replace('col-md-10', 'col-md-12');
    document.getElementById('ButtonSelec8').style.display="block";
});



