//
function somenteNumeros(src) {
    var code = window.event.keyCode;
    return(code>47 && code<58);
}

//
function aplicarMascara(src, mask, forbidden) {
    if(window.event.keyCode == forbidden.charCodeAt(0)) {
        return false;
    }
    
    var i = src.value.length;
    var saida = mask.substring(0,1);
    var texto = mask.substring(i)
    if (texto.substring(0,1) != saida)
    {
        src.value += texto.substring(0,1);
    }
}

// desabilitar enter dos formularios
function stopRKey(evt) { 
    var evt = (evt) ? evt : ((event) ? event : null); 
    var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
    if ((evt.keyCode == 13) && (node.type=="text"))  {
        return false;
    } 
}
document.onkeypress = stopRKey;

//
function setFocus(id) {
    var element = document.getElementById(id);
    if (element && element.focus) {
        try { element.focus(); } catch(err){}
    }
}

function tabOnSearch(input, button, evento) {
    if(input.value != '' && evento.keyCode == 9) 
        button.click();
}

function tabOnSearch(input, button, evento) {
    if(input.value != '' && evento.keyCode == 9) 
        button.click();
}

function selectOnEnter(input, button) {
    if(input.value != '' && window.event.keyCode == 13) 
        button.click();
}

// FOORMATAR MOEDA
function currencyFormatted(amount) {
    var formatedValue = amount;
    var real = '';
    var cents = '';
    var temp = [];
    var i = 0;
    var j = 0;
    var k = 0;

    formatedValue = clearString(formatedValue.toString(), "0123456789");
    if(formatedValue.length > 2) {
        real = formatedValue.substr(0, formatedValue.length - 2);
        real = "" + parseInt(real, 10);
        cents = formatedValue.substr(formatedValue.length - 2, 2);

        if(real.length > 3) {
            temp = [];
            for(i = real.length - 1, j = 1, k = 0; i > 0 ; i--, j++) {
                if((j % 3) == 0) {
                    temp.push(real.substr(i, 3));
                    k++;
                }
            }
            temp.reverse();
            real = real.substr(0, real.length - (3 * k)) + '.' + temp.join('.');
        }
        formatedValue = real + ',' + cents;
    }
    return formatedValue;
}

// FOORMATAR MOEDA
// onkeyup="this.value = currencyFormattedNoCents(this.value)"
function currencyFormattedNoCents(amount) {
    var formatedValue = amount;
    var real = '';
    var cents = '';
    var temp = [];
    var i = 0;
    var j = 0;
    var k = 0;

    formatedValue = clearString(formatedValue.toString(), "0123456789");
    if(formatedValue.length > 2) {
        real = formatedValue;
        real = "" + parseInt(real, 10);
        //cents = formatedValue.substr(formatedValue.length - 2, 2);

        if(real.length > 3) {
            temp = [];
            for(i = real.length - 1, j = 1, k = 0; i > 0 ; i--, j++) {
                if((j % 3) == 0) {
                    temp.push(real.substr(i, 3));
                    k++;
                }
            }
            temp.reverse();
            real = real.substr(0, real.length - (3 * k)) + '.' + temp.join('.');
        }
        formatedValue = real;
    }
    return formatedValue;
}

function clearString(value, validCharacters) {
    var result = '';
    var index = -1;
    var i = 0;

    for(i = 0; i < value.length; i++) {
        index = validCharacters.indexOf(value.charAt(i));
        if(index > -1) {
            result += validCharacters.charAt(index);
        }
    }
    return result;
}

// Multiplicao de valor Unitario * Quantidade
//onkeyup="this.value = currencyFormatted(this.value); document.getElementById('formEdicao:tabs:prod_vl_total').value = somaProdutos(this.value, document.getElementById('formEdicao:tabs:prod_vl_qtd').value)"
function somaProdutos(money, amount) {
    var formatedValue = money; 
    
    formatedValue = clearString(formatedValue.toString(), "0123456789");
       
    var result = '';
    if((formatedValue != null && formatedValue != '') && (amount != null && amount != '')){
        formatedValue = (amount * formatedValue);
        result = currencyFormatted(formatedValue);
    }
    
    return result;
   
}

// Calculo de Massa Corporal Peso/Altura
function calculaMassaCorp(peso, altura) {
    
    var quadrado = (altura * altura);
    var calculo = (peso/quadrado);
    
    return calculo;
   
}

// Calculo de Massa Corporal Peso/Altura2
function calculaMassaCorp(value, value_) {
    var massaCorp = '';
    massaCorp = value / ( value_ * 2);  
    return massaCorp;
   
}

function getURLParameter(name) {
    return decodeURI(
        (RegExp(name + '=' + '(.+?)(&|$)').exec(location.search)||[,null])[1]
    );
}

function getURLParameter2(name) {
	return decodeURIComponent((location.search.match(RegExp("[?|&]" + name
			+ '=(.+?)(&|$)')) || [ , null ])[1]);
}

