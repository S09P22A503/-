/* eslint-disable react-hooks/exhaustive-deps */
import { useEffect, useRef, useState } from "react";
import styled from "styled-components";
import PropTypes from "prop-types";
import { ReactComponent as Chat } from "../../assets/icons/chat.svg";
import { ReactComponent as DropdownIcon } from "../../assets/icons/right-full-arrow-icon.svg";

// ----------------------------------------------------------------------------------------------------

function Dropdown({ width, data, shape, selectedOption, placeholder, onChange }) {
    const [isOpen, setIsOpen] = useState(false);
    const [iconRotation, setIconRotation] = useState(0);
    const dropdownRef = useRef();
    useEffect(() => {
        const handleOutsideClick = (event) => {
            if (dropdownRef.current && !dropdownRef.current.contains(event.target)) {
                setIsOpen(false);
                setIconRotation(0);
            }
        };
        document.addEventListener("click", handleOutsideClick);
        return () => {
            document.removeEventListener("click", handleOutsideClick);
        };
    }, []);

    const handleOptionClick = (id) => {
        const selected = data.find((option) => option.id === id);
        onChange({ ...selected });
        setIsOpen(false);
        setIconRotation(0);
    };

    const toggleDropdown = () => {
        setIsOpen(!isOpen);
        setIconRotation(isOpen ? 0 : 90);
    };

    return (
        <DropdownWrapper ref={dropdownRef} width={width}>
            <DropdownButton onClick={toggleDropdown} shape={shape}>
                <DropdownChatIconWrapper>
                    <Chat />
                </DropdownChatIconWrapper>
                {selectedOption.label ? (
                    <div>{selectedOption.label}</div>
                ) : (
                    <div>{placeholder}</div>
                )}
                <DropdownIconWrapper iconRotation={iconRotation}>
                    <DropdownIcon width="25" height="25" />
                </DropdownIconWrapper>
            </DropdownButton>
            {data.length > 0 && (
                <DropdownContent open={isOpen} width={width}>
                    {data.map((option) => (
                        <DropdownOption
                            key={option.id}
                            onClick={() => handleOptionClick(option.id)}
                        >
                            <div>
                                {option.img &&
                                    (typeof option.img === "string" ? (
                                        <DropdownItemImg src={option.img} alt="dropdownIcon" />
                                    ) : (
                                        <option.img />
                                    ))}
                            </div>
                            <div>{option.label}</div>
                        </DropdownOption>
                    ))}
                </DropdownContent>
            )}
        </DropdownWrapper>
    );
}

Dropdown.propTypes = {
    width: PropTypes.string,
    data: PropTypes.arrayOf(
        PropTypes.shape({
            id: PropTypes.number.isRequired,
            label: PropTypes.string.isRequired,
            img: PropTypes.oneOfType([PropTypes.string, PropTypes.elementType]).isRequired,
        }),
    ),
    shape: PropTypes.oneOf(["positive", "negative"]),
    selectedOption: PropTypes.shape({
        id: PropTypes.number.isRequired,
        label: PropTypes.string.isRequired,
        img: PropTypes.oneOfType([PropTypes.string, PropTypes.elementType]).isRequired,
    }),
    placeholder: PropTypes.string,
    onChange: PropTypes.func,
};

Dropdown.defaultProps = {
    width: "200px",
    data: [],
    shape: "positive",
    selectedOption: { id: 0, label: ""},
    placeholder: "",
    onChange: () => {},
};

// ----------------------------------------------------------------------------------------------------

const DropdownWrapper = styled.div`
    display: flex;
    flex-direction: column;
    position: relative;
    min-width: 120px;
    width: ${({ width }) => width};
    border: none;
    font-size: 20px;
    font-weight: 700;
    gap: 5px;
`;

const DropdownButton = styled.div`
    position: relative;
    height: 50px;
    display: flex;
    justify-content: space-between;
    border-radius: 10px;
    align-items: center;
    padding: 0 15px;
    cursor: pointer;
    background-color: #eee3f1;
        border: 3px solid #eee3f1;
        color: rgba(0, 0, 0, 0.5);
    div {
        font-size: 20px;
    }
`;

const DropdownContent = styled.div`
    min-width: ${(props) => props.width};
    position: absolute;
    top: 110%;
    overflow: hidden;
    display: ${(props) => (props.open ? "block" : "none")};
    background-color: #ffffff;
    border: 2px solid rgba(0, 0, 0, 0.5);
    border-radius: 10px;
`;

const DropdownOption = styled.div`
    display: flex;
    flex-direction: row;
    align-items: center;
    gap: 20px;
    font-weight: 400;
    padding: 15px;
    color: rgba(0, 0, 0, 0.5);
    &:hover {
        background-color: #eee3f1;
        cursor: pointer;
    }
    div {
        font-size: 20px;
    }
`;

const DropdownIconWrapper = styled.div`
    display: block;
    width: 25px;
    height: 25px;
    transform: rotate(${({ iconRotation }) => iconRotation}deg);
    transition: all 0.2s ease-in-out;
    svg path {
        stroke: rgba(0, 0, 0, 0.5);
    }
`;

const DropdownChatIconWrapper = styled.div`
    display: block;
    width: 25px;
    height: 25px;
    svg path {
        stroke: rgba(0, 0, 0, 0.5);
    }
`;

const DropdownItemImg = styled.img`
    width: 25px;
    height: 25px;
`;

// ----------------------------------------------------------------------------------------------------

export default Dropdown;
