<template>
  <body>
  <!--display-->
    <div id="app" class="calculator">
      <div class="display">{{ display }}</div>
      <!--calculator keys-->
      <div class="keys">
        <div class="row">
          <button @click="onClick('percent')" value="%" class="operator">%</button>
          <button @click="clear()" value="CE" class="operator">CE</button>
          <button @click="clear()" value="C" class="operator">C</button>
          <button @click="del()" value="&#9003;" class="operator">
            &#9003;
          </button>
        </div>
        <div class="row">
          <button @click="onClick('inverse')" value="1/x" class="operator">1/x</button>
          <button @click="onClick('square')" value="x<sup>2</sup>" class="operator">x<sup>2</sup></button>
          <button @click="onClick('sqrt')" value="&#8730;" class="operator">&#8730;</button>
          <button @click="addOperator('divide')" value="÷" class="operator">÷</button>
        </div>
        <div class="row">
          <button @click="append('7')" value="7">7</button>
          <button @click="append('8')" value="8">8</button>
          <button @click="append('9')" value="9">9</button>
          <button @click="addOperator('multiply')" value="×" class="operator">×</button>
        </div>
        <div class="row">
          <button @click="append('4')" value="4">4</button>
          <button @click="append('5')" value="5">5</button>
          <button @click="append('6')" value="6">6</button>
          <button @click="addOperator('subtract')" value="-" class="operator">-</button>
        </div>
        <div class="row">
          <button @click="append('1')" value="1">1</button>
          <button @click="append('2')" value="2">2</button>
          <button @click="append('3')" value="3">3</button>
          <button @click="addOperator('add')" value="+" class="operator">+</button>
        </div>
        <div class="row">
          <button @click="sign()" value="+/-" class="operator">+/-</button>
          <button @click="append('0')" value="0">0</button>
          <button @click="decimal()" value="." class="operator">.</button>
          <button @click="equal()" value="=" class="operator">=</button>
        </div>
      </div>
    </div>
  </body>
</template>

<script>
import axios from 'axios';
export default {
  data() {
    return {
      previous: "",
      display: 0,
      operator: "",
      answer:"",
      operatorClicked: false
    };
  },
  methods: {
    clear() {
      this.display = 0;
    },
    del() {
    if(!this.operatorClicked){
      this.display = this.display.slice(0, -1);
      if(this.display=="")
         this.display=0;
      }
    },
    sign() {
      this.display = this.display < 0 ? (this.display = this.display - this.display * 2) : (this.display = this.display - this.display * 2);
    },
    append(number) {
      if (this.operatorClicked === true ) {
        this.previous=this.display;
        this.display = '';
        this.operatorClicked = false;
      }
      else if(this.display==this.answer)
         this.display='';
      this.display =this.display === 0 ? (this.display = number) : '' + this.display + number;
      
    },
    addOperator(operator) {
      if (this.operatorClicked == false) {
        this.operator=operator;
        this.operatorClicked = true;
      }
    },
    decimal() {
      if (this.display.indexOf('.') === -1) {
        this.append('.');
      }
    },
    //method for 2 variables operations
    equal(){
      if(this.operatorClicked==false){
      var v=this;
      if(v.operator!=""){
      axios.get('http://localhost:8085/calculate',{
      params:{
      first: v.previous,
      second: v.display,
      operation: v.operator
      }
      })
      .then(function(response){
      v.display=response.data;
      v.answer=v.display;
      v.operator="";
      v.previous="";
      })
     }
     }
    },

    //method for a one variable operations
    onClick(operator){
    
      this.operator=operator;
      var v=this;
      axios.get('http://localhost:8085/click',{
      params:{
      number: v.display,
      operation: v.operator
      }
      })
      .then(function(response){
      v.display=response.data;
      })

    }
  }
  
};

</script>

<style>
* {
  padding: 0;
  margin: 0;
}

body {
  width: 100vw;
  height: 100vh;
  overflow: hidden;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: white;
  font-family: sans-serif;
}

.calculator {
  width: 320px;
  background-color: rgb(205, 235, 235);
  box-shadow: 5px 8px 8px -2px rgba(142, 242, 255, 0.61);
}

.display,
.answer {
  grid-column: 1 / 5;
  display: flex;
  align-items: center;
}

.display {
  width: 100%;
  height: 100px;
  border: 2px black;
  font-weight: 500;
  color: black;
  font-size: 2rem;
  box-sizing: border-box;
  overflow: hidden;
  text-overflow: clip;
}

.row {
  display: flex;
}
button {
  width: 80px;
  height: 50px;
  border: 2px black;
  outline: black;
  font-size: 1.5rem;
  background-color: white;
  color: black;
  margin: 1px;
  box-shadow: 7px 6px 28px 1px rgba(0, 0, 0, 0.24);
}

button:hover {
  cursor: pointer;
  transform: scale(0.98);
  box-shadow: 3px 2px 22px 1px rgba(0, 0, 0, 0.24);
}

.operator {
  background-color: lightblue;
  color: #000;
}
</style>
