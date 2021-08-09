#language:ru
#encoding:UTF-8

@newTest
  Функционал: Проверка сайта saucedemo.com

    Сценарий: Проверка, что мы перейдём на главную страницу Saucedemo и авторизуемся
      Допустим открыта страница https://www.saucedemo.com/
      И вводим данные для авторизации и авторизуемся
      Тогда происходит успешная авторизация на сайт https://www.saucedemo.com/ и страница содержит продукты

    Сценарий: Приобретение рюкзака и фонарика на сайте Saucedemo
      Допустим открываем страницу https://www.saucedemo.com/
      И вводим логин, пароль и авторизуемся
      Когда добавляем Backpack и Bike Light в корзину и нажимаем Checkout
      Тогда переходим к заполнению данных заказчика и нажимаем Continue
      И подтвержаем заказ нажатием на поле Finish

    Структура сценария: Проверка цены вещи <itemName>
      Допустим пользователь открывает страницу https://www.saucedemo.com/
      И вводит логин, пароль и авторизуется
      Когда пользователь выполняет нажатие на ссылку "<itemName>"
      Тогда цена вещи равна "<itemPrice>"
      Примеры:
        |itemName                         |itemPrice  |
        |Sauce Labs Backpack              |$29.99     |
        |Sauce Labs Bike Light            |$9.99      |
        |Sauce Labs Bolt T-Shirt          |$15.99     |
        |Sauce Labs Fleece Jacket         |$49.99     |
        |Sauce Labs Onesie                |$7.99      |
        |Test.allTheThings() T-Shirt (Red)|$15.99     |
