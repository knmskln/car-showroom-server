# Система учёта продаж автомобилей в автосалонах
Клиент-серверное приложение с многопоточным сервером, клиентская часть сделана с использованием javaFX.

[Репозиторий с клиентской частью](https://github.com/knmskln/car-showroom-client)

База данных MySQL, работа с базой данных осуществляется с помощью библиотеки hibernate.

## MVP:

### Меню администратора:

- просмотр продаж (сортировка)
- просмотр и управление всеми пользователями (блокировка/разблокировка продавца, дилера, клиента)
- информация по всем заявкам (просмотр всех заявок, их статусы)

### Меню продавца:

- просмотр заявок от клиента (сортировка)
- передача заявки дилеру или отклонение (статус меняется на «Заявка в обработке у дилера» или «Отклонена»)
- редактирование личных данных

### Меню дилера:

- редактирование личных данных
- просмотр заявок (если есть возможность доставить машину принимаем заявку, статус меняется на «Заявка одобрена»; если нет возможности, соответственно «Заявка отклонена»)
- добавление автомобилей в базу
- удаление автомобилей из базы
- редактирование автомобилей

### Меню клиента:

-  редактирование личных данных
- просмотр автомобилей от всех дилеров (таблица, возможность сортировки) 
- отправление заявки на автомобиль (выбор автомобиля из представленных, статус «Заявка на обработке у продавца»)
- просмотр своих заявок

## Nice to have:

### Меню администратора:
- создание и просмотр статистики (kpi продавца, сортировка продавцов по этому признаку, наглядно смотрим количество продаж каждого продавца)

### По программе:

- при регистрации на указанную почту будет выслано письмо об успешной регистрации.
- при смене статуса заявки на «Одобрено» или «Отклонено» будет выслано соответствующее письмо на почту. 
- у пользователя будет возможность восстановить пароль с помощью электронной почты (пароль сгенерируется и отправится на указанную почту).