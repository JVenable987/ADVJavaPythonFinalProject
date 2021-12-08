import OrderLine
import datetime as LocalDate    # for LocalDate


class Order:
    # private LocalDate date;
    # private String orderNumber;
    # private ArrayList<OrderLine> lines;

    def __init__(self, order_number: str, date: LocalDate):
        self._order_number = order_number
        self._date = date
        self._lines = list()

    def total(self):
        # //TODO
        total = 0
        for ol in self._lines:
            total += ol.subTotal()
        return total

    def ___str__(self):
        # todo: make sure this works
        sb = ""
        sb += "------------------------------------------------------------------------------------------------------\n"
        sb += "Order:[{-20s|%-10s]\n", self._order_number, self._date
        for line in self.lines:
            sb += "--->%-50s\n", line.toString()
        sb += "---> Order Total: ${10.2f}\n", self.total()
        sb += "========================================================================================================"
        return sb

    @property
    def date(self):
        """Return the date"""
        return self._date

    @date.setter
    def date(self, the_date):
        """Set the date"""
        self._date = the_date

    @property
    def order_number(self):
        return self._order_number

    @order_number.setter
    def order_number(self, the_order_number: str):
        self._order_number = the_order_number

    @property
    def lines(self):
        return self._lines

    @lines.setter
    def lines(self, the_lines: list):
        self._lines = the_lines
