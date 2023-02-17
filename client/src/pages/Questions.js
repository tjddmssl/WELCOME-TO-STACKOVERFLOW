import React from 'react'
import Header from '../components/Header'
import QButton from '../components/QButton'
import QLists from '../components/QLists'
import Sidebar from '../components/Sidebar'

function Questions() {
  return (
    <div>
      <h1>Questions page</h1>
      <Header />
      <Sidebar />
      All questions
      <QButton /> <br />
      <QLists />
    </div>
  )
}

export default Questions
