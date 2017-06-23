/* Ukrainian (UTF-8) initialisation for the jQuery UI date picker plugin. */
( function( factory ) {
    if ( typeof define === "function" && define.amd ) {

        // AMD. Register as an anonymous module.
        define( [ "../widgets/datepicker" ], factory );
    } else {

        // Browser globals
        factory( jQuery.datepicker );
    }
}( function( datepicker ) {

    datepicker.regional.uk = {
        closeText: "Закрити",
        prevText: "&#x3C;",
        nextText: "&#x3E;",
        currentText: "Сьогодні",
        monthNames: [ "Січень","Лютий","Березень","Квітень","Травень","Червень",
            "Липень","Серпень","Вересень","Жовтень","Листопад","Грудень" ],
        monthNamesShort: [ "Січ","Лют","Бер","Кві","Тра","Чер",
            "Лип","Сер","Вер","Жов","Лис","Гру" ],
        dayNames: [ "Неділя","Понеділок","Вівторок","Середа","Четвер","П’ятниця","Субота" ],
        dayNamesShort: [ "Нед","Пнд","Вів","Срд","Чтв","Птн","Сбт" ],
        dayNamesMin: [ "Нд","Пн","Вт","Ср","Чт","Пт","Сб" ],
        weekHeader: "Тиж",
        dateFormat: "dd.mm.yy",
        firstDay: 1,
        isRTL: false,
        showMonthAfterYear: false,
        yearSuffix: "" };
    datepicker.setDefaults( datepicker.regional.uk );

    return datepicker.regional.uk;

} ) );
