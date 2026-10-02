import axios from 'axios'
import React, { useEffect, useState } from 'react'


const ExpenseForm = ({getExpenses, editingExpense, setEditingExpense}) => {
  const [title, setTitle] = useState('')
  const [category, setCategory] = useState('')
  const [price, setPrice] = useState('')
  const [date, setDate] = useState('')

  useEffect(()=>{
    
    if(editingExpense){
      setErrors({})
    setTitle(editingExpense.title)
     setCategory(editingExpense.category)
      setPrice(editingExpense.price)
       setDate(editingExpense.date)

       scrollTo({top:0, behavior:'smooth'})
  }

  },[editingExpense])

 

  const handleSubmit = async (e) => {
    e.preventDefault();
    
    const expense = {
      id: editingExpense ? editingExpense.id:0,
      title,
      price,
      category,
      date
    }

    if(editingExpense){
      await updateExpense(expense)
    }else{
    await createExpense(expense)
    }
  }
   async function updateExpense(expense) {
    try {
      const response = await axios.put("http://localhost:1211/expenses", expense)
  
      if(response.status === 202) {
        getExpenses()
        clearForm()
        setEditingExpense(null)
      } else {
        alert("Something went wrong !!!")
      }
    } catch(err) {
      console.log("Some Error occurred:-", err)
    }
  }


  async function createExpense(expense) {
    try {
      const response = await axios.post("http://localhost:1211/expenses", expense)
  
      if(response.status === 201) {
        getExpenses()
        clearForm()
      } else {
        alert("Something went wrong !!!")
      }
    } catch(err) {
      console.log("Some Error occurred:-", err)
    }
  }

  const clearForm = () => {
    setTitle('')
    setCategory('')
    setPrice('')
    setDate('')
  }

  const handleChange = (e) => {
    const { name, value } = e.target

    switch (name) {
      case 'title':
        setTitle(value)
        setErrors(prev=>({ ...prev,title:''}))
        break;
      case 'category':
        setCategory(value)
        setErrors(prev=>({ ...prev,category:''}))
        break;
      case 'price':
        setPrice(value)
        setErrors(prev=>({ ...prev,price:''}))
        break;
      case 'date':
        setDate(value)
        setErrors(prev=>({ ...prev,date:''}))
        break;
    }
  }
  const handleCancel=()=>{
    setEditingExpense(null)
    clearForm()
  }

  return (
    <div className='bg-white rounded-2xl shadow-md p-6 mb-6'>
      <h2 className='text-xl font-semibold text-gray-700 mb-4'>{editingExpense ? 'Edit':'Add'} Expense</h2>

      <form action={"www.ggogle.com"} onSubmit={handleSubmit} className='grid grid-cols-1 md:grid-cols-2 gap-4'>
        {/* Title */}
        <div>
          <label htmlFor="" className='block font-medium text-gray-600 mb-1'>Title</label>
          <input type="text" className='border w-full border-gray-300 rounded-lg px-3 py-2 focus:outline-none focus:border-blue-500' onChange={handleChange} name='title' value={title} />
        </div>

        {/* Category */}
        <div>
          <label htmlFor="" className='block font-medium text-gray-600 mb-1'>Category</label>
          <select id="" className='border w-full border-gray-300 rounded-lg px-3 py-2 focus:outline-none focus:border-blue-500' onChange={handleChange} name='category' value={category}>
            <option value="" >-- Select Category --</option>
            <option value="food" >Food</option>
            <option value="travel" >Travel</option>
            <option value="utilities" >Utilities</option>
            <option value="shopping" >Shopping</option>
            <option value="entertainment" >Entertainment</option>
            <option value="health" >Health</option>
            <option value="education" >Education</option>
            <option value="others" >Others</option>
          </select>
        </div>

        {/* Price */}
        <div>
          <label htmlFor="" className='block font-medium text-gray-600 mb-1'>Price</label>
          <input type="text" className='border w-full border-gray-300 rounded-lg px-3 py-2 focus:outline-none focus:border-blue-500' onChange={handleChange} name='price' value={price} />
        </div>

        {/* Date */}
        <div>
          <label htmlFor="" className='block font-medium text-gray-600 mb-1'>Date</label>
          <input type="date" className='border w-full border-gray-300 rounded-lg px-3 py-2 focus:outline-none focus:border-blue-500' onChange={handleChange} name='date' value={date} />
        </div>

        {/* Add Expense Button */}
        <div className='mt-5'>
          {
            editingExpense ?
            <div className='flex gap-2'>
            <button className='bg-green-500 hover:bg-green-600 px-6 py-3 rounded-lg text-white text-lg font-medium transition-colors duration-200' >
            Update Expense
          </button>
          <button onClick={handleCancel} className='bg-yellow-500 hover:bg-green-600 px-6 py-3 rounded-lg text-white text-lg font-medium transition-colors duration-200' >
            Cancel
          </button>
          </div> :
          
          <button className='bg-green-500 hover:bg-green-600 px-6 py-3 rounded-lg text-white text-lg font-medium transition-colors duration-200' >
            Add Expense
          </button>
}
        </div>
          
      </form>
    </div>
  )
}

export default ExpenseForm