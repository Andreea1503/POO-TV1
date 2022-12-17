# POO TV - part 1 #
```Copyright Spinochi Andreea, 322CA, 2022```

### packages: ###

``` database ```

#### classes: ####

* Database 
  * contains the workflow of the application, and it is an implementation
of a singleton database that keeps track of the current page and makes the
excution decision depending on the type of action initiated

* Page
  * contains the information about the current page, and it is the place where
  the actions are executed using a factory design pattern for every type of
  action

``` changePage``` - package included in ``` database ```
#### classes: ####

* ChangePageActionFactory
  * creates a factory that makes the decision of which action to execute
    depending on the type of the issued action

* ChangePageAction
  * it is an interface that is implemented by all the actions that can be
    executed

* ChangePageActionTo*
    * classes that implement the ChangePageAction interface and execute the
    action to change the page to the one specified in the name of the class

``` onPage ``` - package included in ``` database ```

#### classes: ####

* OnPageActionFactory
  * creates a factory that makes the decision of which action to execute
    depending on the type of the issued action

* OnPageAction
    * it is an interface that is implemented by all the actions that can be
        executed

* OnPageAction*
    * classes that implement the OnPageAction interface and execute the
    action to change something on the current page

``` fileio ```

#### classes: ####

* Input
    * reads the input from the json file

* ActionsInput
    * holds the information about the actions that are read from the json file

* UsersInput
    * holds the information about the users that are read from the json file

* MoviesInput
    * holds the information about the movies that are read from the json file

_The rest of the classes are used to hold details about the users, movies and
actions that are read from the json file._

``` write ```

#### classes: ####

* Write
    * writes the output to the json file

``` Main ```

* contains the main method that creates the database and starts the application


### Design Flow ###

The application starts out in Main. From there, the database is instantiated
and the input is read from the json file. The result of this will be an Input
object that contains the information about the users, movies and actions that
will be given to the database to be processed by calling the method
``` databaseNavigation()```. This method will create a Page object that will be
used to execute the actions.

Firstly, the current page name will be set to "Homepage neautentificat" and the
actions will be parsed. Then, depending on the type of action, the method
``` onPageAction() ``` or ``` changePageAction() ``` will be called. These
methods have a factory design implemented, so that the decision of which action
to execute is made by the factory. The factory will create an object that will
implement the OnPageAction or ChangePageAction interface, and the method
``` execute() ``` will be called on that object. Depending on the type of
method called, the action will be executed on the current page or the page will
be changed. Each method that is executed has a different implementation, and it
is described in the class that implements it.

The output is implemented in the ``` write ``` package. It uses only one method
called ``` writePageError() ``` that writes the output to the json file by
adding every field of the class that is passed as a parameter.

### Design Patterns ###

_Used design patterns:_

* Singleton
    *  the singleton pattern has been used in creating the database since it
  has to be a globally accessible, persistent instance. The variation of
  Singleton used is the one outlined by Bill Pugh since it is an easy to
  implement, efficient and thread-safe variation of the Singleton Pattern.

* Factory
  * this pattern was used in creating both the ``` OnPage ``` and
  ``` ChangePage``` factories since it allows for the easy execution of the
  commands based on a generic interface.

### Notes ###

* At first, I didn't really see how I could implement inheritance, because
the classes that I had to implement were very different from each other,
and inhering every page from another page seemed to make the project very
difficult. However, for the next part I will change my approach and try to
implement inheritance for the pages and for the users(standard and premium).

* Also, for the next part I will implement the Command pattern instead of
the Factory pattern, because it seems to be a better fit for the project and
Factory will be used for other purposes.