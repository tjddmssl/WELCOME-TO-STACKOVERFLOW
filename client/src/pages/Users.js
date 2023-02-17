import React from 'react'
import UserCard from '../components/UserCard'
import Header from '../components/Header'
import Sidebar from '../components/Sidebar'


function Users() {
  return (
   <div>
    <Header />
    <Sidebar />
      <h1>Users page</h1>
      Users <br />
      <input type="text" placeholder='Filter by user'></input>
      <UserCard />
    </div>
   
   ) 
  
}

export default Users
