let state={pipe:0,scenario:0,score:1,answered:0,streak:0,phase:'menu'};
const $=id=>document.getElementById(id);

function render(){
  $('menu').style.display=state.phase==='menu'?'flex':'none';
  $('play').style.display=state.phase==='play'?'block':'none';
  $('reveal').style.display=state.phase==='reveal'?'block':'none';
  $('results').style.display=state.phase==='results'?'flex':'none';
  if(state.phase==='play') renderPlay();
  if(state.phase==='reveal') renderReveal();
  if(state.phase==='results') renderResults();
}

function start(){state.phase='play';render();}

function findScenario(){
  let idx=0;
  for(let i=0;i<SCENARIOS.length;i++){
    if(SCENARIOS[i].pipe===state.pipe){
      if(idx===state.scenario) return{sc:SCENARIOS[i],i};
      idx++;
    }
  }
  return null;
}

function renderPlay(){
  const f=findScenario();
  if(!f){advance();return;}
  const sc=f.sc, p=PIPES[sc.pipe];
  $('p-pipe').innerHTML=`<span style="color:${p.color}">${p.glyph} p=${p.p} · ${p.name}</span>`+
    `<span style="color:#667;font-size:7px"> ${p.desc}</span>`;
  $('p-score').textContent='×'+state.score;
  $('p-setup').textContent=sc.setup;
  $('p-rule').textContent='📏 '+sc.rule;
  const ch=$('p-choices');ch.innerHTML='';
  sc.choices.forEach((c,i)=>{
    const d=document.createElement('div');d.className='choice';
    d.textContent=c.text;
    d.onclick=()=>choose(i);
    ch.appendChild(d);
  });
  $('p-streak').textContent=state.streak>1?'🔥 '+state.streak+' streak':'';
}

function choose(idx){
  const sc=findScenario().sc, c=sc.choices[idx], p=PIPES[sc.pipe];
  state.selectedChoice=idx;
  state.score=c.brk?state.score*p.p:state.score;
  state.streak=c.brk?state.streak+1:0;
  state.answered++;
  state.phase='reveal';
  render();
}

function renderReveal(){
  const sc=findScenario().sc, c=sc.choices[state.selectedChoice], p=PIPES[sc.pipe];
  $('r-result').textContent=c.brk?'RULE BROKEN ✓':'RULE FOLLOWED ✗';
  $('r-result').style.color=c.brk?'#4a9a44':'#9b3da0';
  $('r-mult').textContent=c.brk?'Score × '+p.p:'Score unchanged';
  const el=$('r-choices');el.innerHTML='';
  sc.choices.forEach((ch,i)=>{
    const d=document.createElement('div');
    d.className='rchoice '+(ch.brk?'good':'bad');
    d.innerHTML=`<div>${ch.brk?'🔓':'📏'} ${ch.text}</div>`+
      `<div class="why">${ch.why}</div>`+
      (i===state.selectedChoice?'<div class="you">← your choice</div>':'');
    el.appendChild(d);
  });
  $('r-lesson').textContent=sc.lesson;
  $('r-lesson').style.color=p.color;
  $('r-score').textContent='Score: '+state.score;
  $('r-coherent').textContent=state.score===510510?'S₅ = 0 · THE A.S.S. IS COHERENT':'';
}

function advance(){
  state.scenario++;
  let count=SCENARIOS.filter(s=>s.pipe===state.pipe).length;
  if(state.scenario>=count){
    state.pipe++;state.scenario=0;
    if(state.pipe>=PIPES.length){state.phase='results';render();return;}
  }
  state.phase='play';render();
}

function renderResults(){
  const coh=state.score===510510;
  $('x-s5').textContent=coh?'S₅ = 0':'S₅ > 0';
  $('x-s5').style.color=coh?'#d4a94a':'#9b3da0';
  $('x-msg').textContent=coh?'THE A.S.S. IS COHERENT':'SOME PIPES ARE SILENT';
  $('x-score').textContent='Score: '+state.score;
  $('x-formula').textContent=coh?'2 × 3 × 5 × 7 × 11 × 13 × 17 = 510,510':'';
  $('x-stats').textContent=state.answered+' scenarios · '+state.streak+' final streak';
  $('x-wisdom').textContent=coh?
    'You broke every rule. All seven pipes carry signal.':
    'Some rules held you back. Try again — speak with intent.';
}

function restart(){
  state={pipe:0,scenario:0,score:1,answered:0,streak:0,phase:'menu'};
  render();
}

render();
