import unittest

from util import pad_right


class UtilTestCase(unittest.TestCase):
    def test_pad_right(self):
        o1 = pad_right('123', 5)
        self.assertEqual('12300', o1)


if __name__ == '__main__':
    unittest.main()
