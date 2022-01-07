import './App.css';
import {useState,useEffect} from "react"
import {Api} from "../services/api"

function App() {
let [task,setTask] = useState([]);

  const handleSubmit = (e) =>{
    e.preventDefault()
    verify(e.target.inputTask.value)
  }

  const verify = (e) =>{
    if(e.length >= 10){
      let result;
      result = e.match(/[^a-zA-Z 0-9]+/g);
      {result ? alert("Não pode conter caracteres especiais") : AdicionarTask(e) }
    }else{
      alert("o campo não pode ter menos de 10 caracteres")
    }
  }

  const getTask = (e) =>{
    Api.get("/task").then(response =>{
    try{
      setTask(response.data)
      console.log(task)
    }catch(error){
    console.log(error)
    }
    })
  }

  const AdicionarTask = (e) =>{
    Api.post("/task/post/add/task", {
      nameTask : `${e}`
    }).then((response) =>{
      console.log(response)
      setTask(response)
      window.location.reload();
    }).catch((error) => {
      console.log(error)
    })
  }

  useEffect(() => {
    getTask();
  },[]);
  
  return (
    <>
    <form onSubmit={handleSubmit}>
        <label>Daily Task</label>
        <div>
        <input id="inputTask" type="text" placeholder="Estudar redis"/>
        <input type="submit" value="Salvar task"/>
        </div>
      </form>
      <table>
        <td>Tasks</td>
        {task.length > 0 ?(
          <>
            {task.map((taskContent,key) => {
          return(
          <tr key={key}>{taskContent.nameTask}</tr>
          )
        })}
          </>
        ) : (
          <>
            <tr>parece que você tem nenhuma task</tr>
          </>
        )}
        
        
      </table>
    
    </>
  )
}

export default App;
