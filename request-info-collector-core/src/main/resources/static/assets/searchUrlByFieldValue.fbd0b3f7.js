import{i as e,r as t}from"./index.39ac0d24.js";import{r as a,n as l,a as s,o,m as r,b as i,w as d,g as n,p as c,F as h,f as p}from"./vendor.8bd8fff2.js";a(""),a("");const u={name:"demo",computed:{},data:()=>({myChartStyle:{float:"left",width:"100%",height:"90%"}}),created(){},mounted(){},watch:{category(e,t){console.log(1111,e,t),this.setOptions(e)}},methods:{change(e){this.category=e},initCharts(){this.chart=e(this.$refs.chart_wrap),0!=this.categories.length&&this.setOptions(this.categories[0]),window.addEventListener("resize",(()=>{this.chart.resize()}))},setOptions(e){var a=[],s=[];t.post({url:"/request/info/collector/report/field",data:{url:this.url,category:e}}).then((t=>{console.log(t),a=t.data.labels,s=t.data.values,this.chart.setOption({title:{text:"字段:"+e+"值分布"},tooltip:{trigger:"axis",axisPointer:{type:"shadow"}},legend:{},color:["#d48265","#91c7ae","#749f83","#ca8622","#bda29a","#6e7074","#546570","#c4ccd3","#ff0000","#00ff00","#0000ff"],grid:{left:"3%",right:"4%",bottom:"3%",containLabel:!0},xAxis:{type:"value",boundaryGap:[0,.01]},yAxis:{type:"category",data:a},series:[{name:e,type:"bar",data:s}]})})).catch((e=>{console.log(e),l({showClose:!0,message:"请求错误",type:"warning"})}))}}},f=p("Primary");u.render=function(e,t,a,l,p,u){const m=s("el-input"),y=s("el-button");return o(),r(h,null,[i(m,{modelValue:e.field,"onUpdate:modelValue":t[0]||(t[0]=t=>e.field=t),placeholder:"field",style:{width:"45%"}},null,8,["modelValue"]),i(m,{modelValue:e.value,"onUpdate:modelValue":t[1]||(t[1]=t=>e.value=t),placeholder:"value",style:{width:"45%"}},null,8,["modelValue"]),i(y,{type:"primary"},{default:d((()=>[f])),_:1}),n("div",{ref:"chart_wrap",class:"chart_wrap",style:c(p.myChartStyle)},null,4)],64)};export{u as default};