This Sample Demo project
It consists use-case Of android architecture Component, Here i will be showcasing sample app 
which have Splash Screen, TaskList Screen, TaskDetail
The purpose of this simple app is to demonstrate the below mentioned components 
Component we used:
1) Kotlin
2) MVVM
3) Clean Architecture
4) Hilt dependency injection
5) Repository pattern
6) Rxjava + LiveData
7) DataBindings
8) Navigation Component
9) Unit testing



Screens:
 1) Splash Screen:
	This screen is splash screen to add a logo or brand name.
	This uses flow in it viewmodel
	
 2) TaskList Screen
	This screen shows the list of todo tasks,
	it uses TaskListViewmodel to fetch the task list with help of rxjava.
	we are using livedata to handle ui in this viewmodel
	we used the databinding for listview adapter this.
	
3) TaskDetail Screen
	This screen shows the detail of task

Dependency Injection- Hilt
	we have used hilt as a dependency injection with the help of annotations provided by Hilt
	di package consist of all the module which helps us in creating depencies

ViewModels: TaskListViewmodel and TaskDetailViewModel
	Both the viewmodel are dependent on taskrepository, which we constructor injected. 
	we used Interface IRepository which showcase the dependency inversion principle, unlike passing concrete class
	View models uses Rxjava to process api call, which then passed to livedata to update the UI layer

Testing: 
	here we showcased how viewmodel and Repsoitories can be tested with Mockito


Api Used:
	1) Tasklist : https://dummyjson.com/todos
	2) TaskDetail: https://dummyjson.com/todos/1	
