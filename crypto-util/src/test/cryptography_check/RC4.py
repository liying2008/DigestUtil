from cryptography.hazmat.decrepit.ciphers.algorithms import ARC4
from cryptography.hazmat.primitives.ciphers import Cipher

from util import pad_right, to_base64

KEY = '1234567'
PLAIN_TEXT = '中文abc123'
plain_data = bytes(PLAIN_TEXT, 'utf-8')
IV = b'n834nbf#'


def rc4():
    key = bytes(pad_right(KEY, 16, '0'), 'utf-8')
    cipher = Cipher(ARC4(key), None)
    encryptor = cipher.encryptor()
    ct = encryptor.update(plain_data) + encryptor.finalize()
    # [RC4]: zC5bsNXmT5JJCzWh
    print(f'[RC4]: {to_base64(ct)}')

    decryptor = cipher.decryptor()
    pt = decryptor.update(ct) + decryptor.finalize()
    assert pt == plain_data


if __name__ == '__main__':
    rc4()
