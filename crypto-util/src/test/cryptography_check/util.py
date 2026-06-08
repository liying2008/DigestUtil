import base64


def pad_right(origin: str, target_size, character: str = '0') -> str:
    return origin + character * (target_size - len(origin))

def to_base64(origin: bytes) -> str:
    return base64.b64encode(origin).decode()
