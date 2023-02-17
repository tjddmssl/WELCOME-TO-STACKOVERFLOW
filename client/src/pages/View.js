import React from 'react'
import AnswerForm from '../components/AnswerForm'
import QButton from '../components/QButton'
import Header from '../components/Header'
import Sidebar from '../components/Sidebar'

function View() {
  return (
    <div>
      <h1>View page</h1> <br />
      <Header />
      <Sidebar />
      title, informatino, body <QButton />
      <AnswerForm />
    </div>
  )
}

export default View
